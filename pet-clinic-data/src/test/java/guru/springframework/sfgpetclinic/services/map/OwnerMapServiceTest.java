package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    private final String lastName = "Ivanov";
    OwnerMapService ownerMapService;
    final Long ownerId = 1L;
    final Long ownerId2 = 2L;
    final Long ownerId3 = 3L;

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetServiceMap());
        ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();
        assertEquals(1, ownerSet.size());
    }

    @Test
    void deleteById() {
        Owner owner3 = Owner.builder().id(ownerId3).build();
        ownerMapService.save(owner3);
        ownerMapService.deleteById(owner3.getId());
        assertFalse(ownerMapService.findAll().contains(owner3));
    }

    @Test
    void delete() {
    }

    @Test
    void saveExistingId() {
        Owner owner2 = Owner.builder().id(ownerId2).build();
        Owner savedOwner = ownerMapService.save(owner2);
        assertEquals(ownerId2, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner owner3 = Owner.builder().build();
        Owner savedOwner = ownerMapService.save(owner3);

        assertNotNull(savedOwner.getId());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findByLastName() {
        Owner smith = ownerMapService.findByLastName(lastName);
        assertNotNull(smith);
        assertEquals(ownerId, smith.getId());

    }

}