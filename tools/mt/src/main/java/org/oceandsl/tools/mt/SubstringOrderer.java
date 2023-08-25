/***************************************************************************
 * Copyright (C) 2023 OceanDSL (https://oceandsl.uni-kiel.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/
package org.oceandsl.tools.mt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.text.similarity.LevenshteinDistance;

import kieker.analysis.exception.InternalErrorException;
import kieker.analysis.generic.graph.clustering.Clustering;
import kieker.analysis.generic.graph.clustering.OPTICS;
import kieker.analysis.generic.graph.clustering.OPTICSDataGED;
import kieker.analysis.generic.graph.clustering.OpticsData;
import kieker.analysis.generic.graph.mtree.IDistanceFunction;
import kieker.analysis.generic.graph.mtree.MTree;

import org.oceandsl.analysis.generic.Table;
import org.oceandsl.analysis.generic.data.MoveOperationEntry;

/**
 * @author reiner
 *
 */
public class SubstringOrderer {

    private final Table<String, MoveOperationEntry> table;

    public SubstringOrderer(final Table<String, MoveOperationEntry> table) {
        this.table = table;
    }

    public void computeDistances() {
        final LevenshteinDistance distance = new LevenshteinDistance();
        for (int i = 0; i < this.table.getRows().size(); i++) {
            final MoveOperationEntry a = this.table.getRows().get(i);
            for (int j = i + 1; j < this.table.getRows().size(); j++) {
                final MoveOperationEntry b = this.table.getRows().get(j);
                final Integer value = distance.apply(a.getOperationName(), b.getOperationName());
                System.err.printf("%s <-> %s : %d\n", a.getOperationName(), b.getOperationName(), value);
            }
        }
    }

    public void computeOptics() {
        final IDistanceFunction<OpticsData<String>> distanceFunction = new IDistanceFunction<>() {

            final LevenshteinDistance distance = new LevenshteinDistance();

            @Override
            public double calculate(final OpticsData<String> data1, final OpticsData<String> data2) {
                return (double) this.distance.apply(data1.getData(), data2.getData())
                        / (double) (data1.getData().length() + data2.getData().length());
            }
        };

        final MTree<OpticsData<String>> mtree = new MTree<>(distanceFunction, null);

        final double clusteringDistance = 0.12;

        final int minPtr = 1;
        final List<OpticsData<String>> models = new ArrayList<>();

        final IDistanceFunction<String> dis2 = new IDistanceFunction<>() {

            final LevenshteinDistance distance = new LevenshteinDistance();

            @Override
            public double calculate(final String data1, final String data2) {
                return (double) this.distance.apply(data1, data2) / (double) (data1.length() + data2.length());
            }
        };
        final OPTICSDataGED<String> ged = new OPTICSDataGED<>(dis2);

        this.table.getRows().forEach(entry -> {
            final OpticsData<String> data = new OpticsData<>(entry.getOperationName(), ged);
            models.add(data);
            try {
                mtree.add(data);
            } catch (final InternalErrorException e) {
                e.printStackTrace();
            }
        });

        final OPTICS<String> optics = new OPTICS<>(mtree, clusteringDistance, minPtr, models);
        final List<OpticsData<String>> opticsResults = optics.calculate();

        for (final OpticsData<String> model : opticsResults) {
            System.err.println(Double.toString(model.getReachabilityDistance()) + " and core: "
                    + Double.toString(model.getCoreDistance()));
        }
        final Clustering<String> clustering = new Clustering<>();

        Set<String> currentCluster = clustering.getNoise();

        for (final OpticsData<String> model : opticsResults) {
            if (model.getReachabilityDistance() == OpticsData.UNDEFINED
                    || model.getReachabilityDistance() > clusteringDistance) {
                if (model.getCoreDistance() <= clusteringDistance && model.getCoreDistance() != OpticsData.UNDEFINED) {
                    final Set<String> newCluster = new HashSet<>();
                    clustering.addCluster(newCluster);
                    newCluster.add(model.getData());
                    currentCluster = newCluster;
                } else {
                    clustering.getNoise().add(model.getData());
                }
            } else {
                currentCluster.add(model.getData());
            }
        }

        System.err.println("---------");
        opticsResults.forEach(element -> {
            System.err.printf("%s\n", element.getData());
        });
        System.err.println("---------");
        clustering.getClusters().forEach(cluster -> {
            System.err.println(".");
            cluster.forEach(element -> {
                System.err.println(element);
            });
        });
    }
}
