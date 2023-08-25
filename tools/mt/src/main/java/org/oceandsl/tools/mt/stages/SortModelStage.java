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
package org.oceandsl.tools.mt.stages;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;

import org.csveed.annotations.CsvCell;

import teetime.stage.basic.AbstractFilter;

import org.oceandsl.analysis.generic.Table;
import org.oceandsl.analysis.generic.data.MoveOperationEntry;
import org.oceandsl.tools.mt.EOrder;
import org.oceandsl.tools.mt.SortCriterium;
import org.oceandsl.tools.mt.SortDescriptor;

public class SortModelStage extends AbstractFilter<Table<String, MoveOperationEntry>> {

    private final SortDescriptor sortDescriptor;

    public SortModelStage(final SortDescriptor sortDescriptor) {
        this.sortDescriptor = sortDescriptor;
    }

    @Override
    protected void execute(final Table<String, MoveOperationEntry> table) throws Exception {
        table.getRows().sort(new Comparator<MoveOperationEntry>() {

            @Override
            public int compare(final MoveOperationEntry left, final MoveOperationEntry right) {
                for (final SortCriterium criterium : SortModelStage.this.sortDescriptor.getSortCriteria()) {
                    try {
                        final String leftValue = this.getValue(left, criterium);
                        final String rightValue = this.getValue(right, criterium);

                        if (leftValue == null || rightValue == null) {
                            SortModelStage.this.logger.error("No values for criterium {} found.",
                                    criterium.getColumnName());
                        } else {
                            int result;
                            if (criterium.getOrder() == EOrder.ASCENDING) {
                                result = this.ascending(leftValue, rightValue);
                            } else {
                                result = this.decending(leftValue, rightValue);
                            }
                            if (result != 0) {
                                return result;
                            }
                        }
                    } catch (IllegalArgumentException | IllegalAccessException | NoSuchMethodException
                            | SecurityException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                return 0;
            }

            private int decending(final String leftValue, final String rightValue) {
                return -1 * leftValue.compareTo(rightValue);
            }

            private int ascending(final String leftValue, final String rightValue) {
                return leftValue.compareTo(rightValue);
            }

            private String getValue(final MoveOperationEntry entry, final SortCriterium criterium)
                    throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException, SecurityException,
                    InvocationTargetException {
                final String name = criterium.getColumnName();
                for (final Field field : entry.getClass().getDeclaredFields()) {
                    final CsvCell[] annotations = field.getAnnotationsByType(CsvCell.class);
                    if (annotations.length > 0) {
                        if (annotations[0].columnName().equals(name)) {
                            final Method method = entry.getClass().getMethod(this.makeMethodName(field.getName()),
                                    null);
                            return (String) method.invoke(entry);
                        }
                    }
                }
                return null;
            }

            private String makeMethodName(final String name) {
                return "get" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
            }
        });

        this.outputPort.send(table);
    }

}
