
/*
 * Licensed to Taxi-Moto-Tricycle under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Taxi-Moto-Tricycle licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package mg.majungaville.taximototricycle.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author andriantomanga
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TmtWsResult {

	// {"r":"1","id":"2172","n":"157","i":"6354MD","t":"0340501635","p":""}
	// mapping
	private Integer n;
	private String i;
	private String t;

	public Integer getN() {
		return n;
	}

	public void setN(Integer n) {
		this.n = n;
	}

	public String getI() {
		return i;
	}

	public void setI(String i) {
		this.i = i;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	@Override
	public String toString() {
		return "TmtWsResult [n=" + n + ", i=" + i + ", t=" + t + "]";
	}

}
