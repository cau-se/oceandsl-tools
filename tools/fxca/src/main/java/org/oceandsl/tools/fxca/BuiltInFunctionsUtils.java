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
 * @since 1.3.0
 *
 */
public class BuiltInFunctionsUtils {

    public static List<FortranOperation> createOperations() {
        final List<FortranOperation> operations = new ArrayList<>();

        operations.add(BuiltInFunctionsUtils.createOperation("abs", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("allocated", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("aint", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("anint", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("acos", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("asin", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("atan", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("cbrt", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("char", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("conjg", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("cos", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("cosh", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("dble", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("dim", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("erf", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("exp", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("float", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("imag", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("ichar", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("ifix", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("index", 2, true));
        operations.add(BuiltInFunctionsUtils.createOperation("int", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("len", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("len_trim", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("log", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("alog", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("dlog", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("clog", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("zlog", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("cdlog", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("log10", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("alog10", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("dlog10", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("max", 2, true));
        operations.add(BuiltInFunctionsUtils.createOperation("max0", 2, true));
        operations.add(BuiltInFunctionsUtils.createOperation("amax0", 2, true));
        operations.add(BuiltInFunctionsUtils.createOperation("max1", 2, true));
        operations.add(BuiltInFunctionsUtils.createOperation("amax1", 2, true));
        operations.add(BuiltInFunctionsUtils.createOperation("dmax1", 2, true));
        operations.add(BuiltInFunctionsUtils.createOperation("min", 2, true));
        operations.add(BuiltInFunctionsUtils.createOperation("min0", 2, true));
        operations.add(BuiltInFunctionsUtils.createOperation("amin0", 2, true));
        operations.add(BuiltInFunctionsUtils.createOperation("min1", 2, true));
        operations.add(BuiltInFunctionsUtils.createOperation("amin1", 2, true));
        operations.add(BuiltInFunctionsUtils.createOperation("dmin1", 2, true));
        operations.add(BuiltInFunctionsUtils.createOperation("mod", 2));
        operations.add(BuiltInFunctionsUtils.createOperation("nint", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("real", 2));
        operations.add(BuiltInFunctionsUtils.createOperation("sign", 2));
        operations.add(BuiltInFunctionsUtils.createOperation("sin", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("sinh", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("sqrt", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("tan", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("tanh", 1));
        operations.add(BuiltInFunctionsUtils.createOperation("trim", 1));

        return operations;
    }

    private static FortranOperation createOperation(final String name, final int arguments) {
        return BuiltInFunctionsUtils.createOperation(name, arguments, false);
    }

    private static FortranOperation createOperation(final String name, final int arguments,
            final boolean variableArguments) {
        final FortranOperation operation = new FortranOperation(name, null, true, variableArguments);
        for (int i = 0; i < arguments; i++) {
            final String label = "v" + i;
            final FortranParameter parameter = new FortranParameter(label, i);
            parameter.setDirection(EDirection.READ);
            operation.getParameters().put(label, parameter);
        }

        return operation;
    }

}
