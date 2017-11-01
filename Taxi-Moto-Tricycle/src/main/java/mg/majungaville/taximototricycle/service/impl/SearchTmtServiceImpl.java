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
