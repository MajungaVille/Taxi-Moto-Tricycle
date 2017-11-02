
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import mg.majungaville.taximototricycle.exception.ServiceException;
import mg.majungaville.taximototricycle.model.Tmt;
import mg.majungaville.taximototricycle.model.TmtWsResult;
import mg.majungaville.taximototricycle.service.SearchTmtService;
import mg.majungaville.taximototricycle.util.StringUtil;

/**
 * @author andriantomanga
 */
@Service
public class SearchTmtServiceImpl implements SearchTmtService {

	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(SearchTmtServiceImpl.class);

	/** Pattern URL pour les invocations ws */
	private static final String URL_FORMAT = "http://majungaville.mg/tmt/ws/api.php?f=s&c=%s&v=%s";

	/**
	 * {@inheritDoc}}
	 */
	@Override
	public Tmt findTmt(final String criteria, final String value) throws ServiceException {

		final Tmt tmt = new Tmt();
		tmt.setSearchCriteria(criteria);
		tmt.setSearchValue(value);
		tmt.setSuccess(false);
		try {

			final RestTemplate restTemplate = new RestTemplate();

			List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
			MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
			converter.setSupportedMediaTypes(Arrays.asList(MediaType.ALL));
			messageConverters.add(converter);
			restTemplate.setMessageConverters(messageConverters);
			final TmtWsResult result = restTemplate.getForObject(String.format(URL_FORMAT, criteria, cleanValue(value)),
					TmtWsResult.class);

			if (result != null) {
				tmt.setNumber(result.getN());
				tmt.setImmatricuation(result.getI());
				tmt.setPhoneNumber(result.getT());
				tmt.setSuccess(tmt.getImmatricuation() != null && tmt.getNumber() != null);
				LOGGER.info("Succes de la recherche. Resultat : {}", result.toString());
			}
		} catch (Exception ex) {
			LOGGER.error("Erreur durant la recherche", ex);
			throw new ServiceException("Erreur lors de la recherche de TMT", ex);
		}

		return tmt;
	}

	/**
	 * Nettoie la valeur saisie
	 * 
	 * @param value
	 *            la valeur a nettoyer
	 * @return la valeur nettoyee
	 */
	private String cleanValue(String value) {
		if (StringUtil.isEmpty(value)) {
			return value;
		}
		// supprimer les espaces et mettre en majuscule
		return value.trim().toUpperCase().replace(" ", StringUtil.EMPTY).replace("\t", StringUtil.EMPTY);
	}

}
