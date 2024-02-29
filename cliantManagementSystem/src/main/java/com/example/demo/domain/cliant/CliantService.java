package com.example.demo.domain.cliant;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CliantService {
	
	private final CliantRepository cliantRepository;
	
	public List<CliantEntity> findAll() {
		return cliantRepository.findAll();
	}
	
	//作成
	@Transactional
	public void create(String sei, String mei, String mailaddress, String tellnumber, String region, String industry) {
		if (tellnumber == "") {
			tellnumber = "000-0000-0000";
		}
		if (region == "") {
			region = "---";
		}
		if (industry == "") {
			industry = "---";
		}
		cliantRepository.insert(sei, mei, mailaddress, tellnumber, region, industry);
	}

	public CliantEntity findById(long id) {
		return cliantRepository.findById(id);
	}
	
	@Transactional
	public List<CliantEntity> findByRegion(String region) {
		return cliantRepository.findByRegion(region);
	}
	
	
	//検索とフィルタリング
	public List<CliantEntity> searchAndFilter(String sei, String mei, String mail, String tell, String region, String industry) {
		return cliantRepository.searchAndFilter(sei, mei, mail, tell, region, industry);
	}
	
	
	public void deleteById(long id) {
		cliantRepository.deleteById(id);
	}

	public void updateById(long id, String sei, String mei, String mailaddress, String tellnumber, String region,
			String industry) {
		cliantRepository.updateById(id,sei,mei,mailaddress,tellnumber, region,industry);
	}

	

}
