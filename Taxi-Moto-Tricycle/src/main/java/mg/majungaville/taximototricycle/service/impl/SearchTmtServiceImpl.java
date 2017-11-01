
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
package mg.majungaville.taximototricycle.service.impl;

import org.springframework.stereotype.Service;

import mg.majungaville.taximototricycle.model.Tmt;
import mg.majungaville.taximototricycle.service.SearchTmtService;

@Service
public class SearchTmtServiceImpl implements SearchTmtService {

	@Override
	public Tmt findTmt(String criteria, String value) {

		Tmt tmt = new Tmt();
		tmt.setSearchCriteria(criteria);
		tmt.setSearchValue(value);

		// appel web services sur le serveur de majungaville.mg
		if("xyz".equals(value)) {
			
			tmt.setNumber(111);
			tmt.setImmatricuation("1212MB");
			tmt.setPhoneNumber("0323343322");
		}
		return tmt;
	}

}
