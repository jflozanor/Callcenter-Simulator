/**
 * Copyright 2020 Alexander Herzog
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package parser.symbols;

import org.apache.commons.math3.util.FastMath;

import mathtools.distribution.tools.DistributionRandomNumber;
import parser.coresymbols.CalcSymbolPreOperator;

/**
 * Zufallszahl<br>
 * Wird kein Parameter angegeben, so wird eine Zufallszahl im Bereich [0,1) geliefert,
 * sonst im Bereich [0,x].
 * @author Alexander Herzog
 * @see DistributionRandomNumber#nextDouble()
 */
public final class CalcSymbolPreOperatorRandom extends CalcSymbolPreOperator {
	@Override
	protected Double calc(double[] parameters) {
		if (parameters.length==0) return DistributionRandomNumber.nextDouble();
		if (parameters.length==1) return DistributionRandomNumber.nextDouble()*FastMath.abs(parameters[0]);
		return null;
	}

	@Override
	protected double calcOrDefault(final double[] parameters, final double fallbackValue) {
		if (parameters.length==0) return DistributionRandomNumber.nextDouble();
		if (parameters.length==1) return DistributionRandomNumber.nextDouble()*FastMath.abs(parameters[0]);
		return fallbackValue;
	}

	@Override
	public String[] getNames() {
		return new String[]{"Random"};
	}

	@Override
	protected boolean isDeterministic() {
		return false;
	}
}
