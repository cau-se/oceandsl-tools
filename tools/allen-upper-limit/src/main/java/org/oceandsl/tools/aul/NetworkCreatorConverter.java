/***************************************************************************
 * Copyright (C) 2022 OceanDSL (https://oceandsl.uni-kiel.de)
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
package org.oceandsl.tools.aul;

import com.beust.jcommander.IStringConverter;

import org.oceandsl.tools.aul.stages.FullMeshedNetworkCreator;
import org.oceandsl.tools.aul.stages.INetworkCreator;
import org.oceandsl.tools.aul.stages.LinearConnectionNetworkCreator;
import org.oceandsl.tools.aul.stages.NullNetworkCreator;
import org.oceandsl.tools.aul.stages.StarConnectionNetworkCreator;

/**
 * @author Reiner Jung
 * @since 1.4
 *
 */
public class NetworkCreatorConverter implements IStringConverter<INetworkCreator> {

    private static final Object FULL_MESHED_NETWORK = "full";
    private static final Object LINEAR_NETWORK = "linear";
    private static final Object STAR_NETWORK = "star";

    @Override
    public INetworkCreator convert(final String value) {
        if (NetworkCreatorConverter.FULL_MESHED_NETWORK.equals(value)) {
            return new FullMeshedNetworkCreator();
        } else if (NetworkCreatorConverter.LINEAR_NETWORK.equals(value)) {
            return new LinearConnectionNetworkCreator();
        } else if (NetworkCreatorConverter.STAR_NETWORK.equals(value)) {
            return new StarConnectionNetworkCreator();
        } else {
            return new NullNetworkCreator();
        }
    }

}
