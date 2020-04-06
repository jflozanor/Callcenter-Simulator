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
package mathtools.distribution.tools;

import org.apache.commons.math3.distribution.AbstractRealDistribution;

import mathtools.NumberTools;

/**
 * Liefert zus�tzliche Daten f�r eine Verteilung
 * @author Alexander Herzog
 * @see AbstractDistributionWrapper#getInfo(AbstractRealDistribution)
 */
public class DistributionWrapperInfo {
	/**
	 * Erwartungswert (kann <code>null</code> sein, wenn nicht verf�gbar)
	 */
	public final Double E;

	/**
	 * Standardabweichung (kann <code>null</code> sein, wenn nicht verf�gbar)
	 */
	public final Double Std;

	/**
	 * Vorangestellte Informationen (kann <code>null</code> sein)
	 */
	public final String info1;

	/**
	 * Nachgelagerte Informationen (kann <code>null</code> sein)
	 */
	public final String info2;

	/**
	 * Konstruktor der Klasse
	 * @param E	Erwartungswert (kann <code>null</code> sein, wenn nicht verf�gbar)
	 * @param Std	Standardabweichung (kann <code>null</code> sein, wenn nicht verf�gbar)
	 * @param info1	Vorangestellte Informationen (kann <code>null</code> sein)
	 * @param info2	Nachgelagerte Informationen (kann <code>null</code> sein)
	 */
	public DistributionWrapperInfo(final Double E, final Double Std, final String info1, final String info2) {
		this.E=E;
		this.Std=Std;
		this.info1=info1;
		this.info2=info2;
	}

	/**
	 * Konstruktor der Klasse
	 * @param distribution	Verteilung aus der Erwartungswert und Standardabweichung direkt ausgelesen werden
	 * @param info1	Vorangestellte Informationen (kann <code>null</code> sein)
	 * @param info2	Nachgelagerte Informationen (kann <code>null</code> sein)
	 */
	public DistributionWrapperInfo(final AbstractRealDistribution distribution, final String info1, final String info2) {
		E=distribution.getNumericalMean();
		Std=Math.sqrt(distribution.getNumericalVariance());
		this.info1=info1;
		this.info2=info2;
	}

	/**
	 * Konstruktor der Klasse
	 * @param distribution	Verteilung aus der Erwartungswert und Standardabweichung direkt ausgelesen werden
	 */
	public DistributionWrapperInfo(final AbstractRealDistribution distribution) {
		E=distribution.getNumericalMean();
		Std=Math.sqrt(distribution.getNumericalVariance());
		info1=null;
		info2=null;
	}

	/**
	 * Liefert die Kenngr��en bzw. Parameter einer Verteilung
	 * @return	Kenngr��en bzw. Parameter einer Verteilung
	 */
	public String getShortInfo() {
		final StringBuilder result=new StringBuilder();

		if (info1!=null) result.append(info1);
		if (E!=null) {
			if (result.length()>0) result.append("; ");
			result.append("E="+NumberTools.formatNumber(E,3));
		}
		if (Std!=null) {
			if (result.length()>0) result.append("; ");
			result.append("Std="+NumberTools.formatNumber(Std,3));
		}
		if (E!=null && Std!=null && E>0) {
			if (result.length()>0) result.append("; ");
			result.append("CV="+NumberTools.formatNumber(Std/E,3));
		}
		if (info2!=null) {
			if (result.length()>0) result.append("; ");
			result.append(info2);
		}

		return result.toString();
	}

	/**
	 * Liefert die Kenngr��en bzw. Parameter einer Verteilung (in ausgeschriebener Form)
	 * @return	Kenngr��en bzw. Parameter einer Verteilung (in ausgeschriebener Form)
	 */
	public String getLongInfo() {
		final StringBuilder result=new StringBuilder();

		if (info1!=null) result.append(info1);
		if (E!=null) {
			if (result.length()>0) result.append("; ");
			result.append(DistributionTools.DistMean+" E="+NumberTools.formatNumber(E,4));
		}
		if (Std!=null) {
			if (result.length()>0) result.append("; ");
			result.append(DistributionTools.DistStdDev+" Std="+NumberTools.formatNumber(Std,4));
		}
		if (E!=null && Std!=null && E>0) {
			if (result.length()>0) result.append("; ");
			result.append(DistributionTools.DistCV+" CV="+NumberTools.formatNumber(Std/E,4));
		}
		if (info2!=null) {
			if (result.length()>0) result.append("; ");
			result.append(info2);
		}

		return result.toString();

	}
}
