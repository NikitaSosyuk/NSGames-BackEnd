package com.itis.nsgames.demo.repository;

import com.itis.nsgames.demo.model.Ad;
import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.NamedNativeQuery;
import java.lang.annotation.Native;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface AdRepository extends JpaRepository<Ad, Integer> {
    Optional<Ad> findByIdAndAdState(Integer id, Ad.State state);
    Optional<List<Ad>> findAllByTitleStartsWithIgnoreCaseOrderByTitle(String title);

    @Query(nativeQuery=true, value="SELECT * FROM ad ORDER BY random() LIMIT 100")
    List<Ad> getFeedList();
}
