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
package org.oceandsl.tools.fxca;

import java.util.ArrayList;
import java.util.List;

import org.oceandsl.tools.fxca.model.EDirection;
import org.oceandsl.tools.fxca.model.FortranOperation;
import org.oceandsl.tools.fxca.model.FortranParameter;

/**
 * List of built in functions.
 *
 * @author Reiner Jung
 *
 */
public class BuiltInFunctionsUtils {

    public static List<FortranOperation> createOperations() {
        final List<FortranOperation> operations = new ArrayList<>();

        operations.add(createOperation("abs", 1));
        operations.add(createOperation("aint", 1));
        operations.add(createOperation("anint", 1));
        operations.add(createOperation("acos", 1));
        operations.add(createOperation("asin", 1));
        operations.add(createOperation("atan", 1));
        operations.add(createOperation("cbrt", 1));
        operations.add(createOperation("conjg", 1));
        operations.add(createOperation("cos", 1));
        operations.add(createOperation("cosh", 1));
        operations.add(createOperation("dim", 1));
        operations.add(createOperation("erf", 1));
        operations.add(createOperation("exp", 1));
        operations.add(createOperation("float", 1));
        operations.add(createOperation("imag", 1));
        operations.add(createOperation("log", 1));
        operations.add(createOperation("log10", 1));
        operations.add(createOperation("max", 2, true));
        operations.add(createOperation("min", 2, true));
        operations.add(createOperation("mod", 1));
        operations.add(createOperation("nint", 1));
        operations.add(createOperation("real", 2));
        operations.add(createOperation("sign", 1));
        operations.add(createOperation("sin", 1));
        operations.add(createOperation("sinh", 1));
        operations.add(createOperation("sqrt", 1));
        operations.add(createOperation("tan", 1));
        operations.add(createOperation("tanh", 1));
        operations.add(createOperation("trim", 1));

        return operations;
    }

    private static FortranOperation createOperation(final String name, final int arguments) {
        return createOperation(name, arguments, false);
    }

    private static FortranOperation createOperation(final String name, final int arguments,
            final boolean variableArguments) {
        final FortranOperation operation = new FortranOperation(name, null, variableArguments);
        for (int i = 0; i < arguments; i++) {
            final String label = "v" + i;
            final FortranParameter parameter = new FortranParameter(label, i);
            parameter.setDirection(EDirection.READ);
            operation.getParameters().put(label, parameter);
        }

        return operation;
    }

}
