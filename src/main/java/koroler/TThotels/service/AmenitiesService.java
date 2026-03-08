package koroler.TThotels.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import koroler.TThotels.entity.Amenity;
import koroler.TThotels.repository.AmenitiesRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AmenitiesService {
	
	private final AmenitiesRepository amenitiesRepository;
	
	@Transactional
	public List<Amenity> findOrCreateAll(List<String> names) {  
		/**
		 * Можно было бы сделать немного круче.
		 * 1) Найти batch-операцией все уже имеющиеся в бд amenities
		 * 2) Для не найденных провернуть findOrCreateByName
		 * Но для mvp и так сойдет
		 */
        return names.stream()                                                                                                                                                            
            .map(amenitiesRepository::findOrCreateByName)                                                                                                     
            .toList();                                                                                                                                                                   
    }  
}
