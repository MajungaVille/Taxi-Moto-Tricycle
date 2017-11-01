
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
package mg.majungaville.taximototricycle.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import mg.majungaville.taximototricycle.model.Tmt;
import mg.majungaville.taximototricycle.service.SearchTmtService;

/**
 * @author MajungaVille
 */
@Controller
public class SearchTmtController {

	/** Le logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchTmtController.class);

	/** etiquette pour le critere immatriculation */
	private static final String IMMAT_LABEL = "Immatriculation";

	/** etiquette pour le critere numero */
	private static final String NUMB_LABEL = "Numéro";

	@Autowired
	private SearchTmtService searchTmtService;

	@GetMapping("/search_tmt")
	public String initSearchForm(Model model) {
		LOGGER.info("Initialisation de la page principale en cours ");

		model.addAttribute("tmt", new Tmt());
		return "search_tmt";
	}

	@PostMapping("/search_tmt")
	public String submitSearchForm(@ModelAttribute Tmt tmt, Model model) {
		System.out.println("valeur soumise : " + tmt.getSearchValue());

		LOGGER.info("Recherche en cours des informations relatives à un tmt avec le critère {} ayant la valeur {} ",
				getCriteriaLabel(tmt.getSearchCriteria()), tmt.getSearchValue());

		final Tmt foundTmt = searchTmtService.findTmt(tmt.getSearchCriteria(), tmt.getSearchValue());

		model.addAttribute("foundTmt", foundTmt);

		return "search_tmt";
	}

	private String getCriteriaLabel(String searchCriteria) {
		return "0".equals(searchCriteria) ? IMMAT_LABEL : NUMB_LABEL;
	}
}
