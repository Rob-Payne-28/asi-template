package mil.army.futures.asitemplate.person.service;

import lombok.AllArgsConstructor;
import mil.army.futures.asitemplate.allocations.service.AllocationService;
import mil.army.futures.asitemplate.person.entity.PersonEntity;
import mil.army.futures.asitemplate.person.entity.PersonRequest;
import mil.army.futures.asitemplate.person.entity.PersonResponse;
import mil.army.futures.asitemplate.person.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonService {
    private PersonRepository personRepository;
    private AllocationService allocationService;

    public PersonResponse createPerson(PersonRequest personRequest) {
        var newPersonEntity = personRepository.save(
                PersonEntity.builder()
                        .name(personRequest.getName())
                        .build()
        );

        personRepository.save(allocationService.assignPersonToTeam(newPersonEntity, "Unallocated"));

        return PersonResponse.builder()
                .id(newPersonEntity.getId())
                .name(newPersonEntity.getName())
                .build();
    }

}
