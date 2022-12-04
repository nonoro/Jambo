package jambo.repository;

import jambo.domain.user.IconShop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IconShopRepository extends JpaRepository<IconShop, Long> {
    @Query("select i from IconShop i where i.name like %:name% order by i.saveDate desc")
    Page<IconShop> searchByName(@Param("name") String name, Pageable page);

    IconShop findByName(@Param("name") String name);

    IconShop findByFileName(String fileName);

    Page<IconShop> findAllByOrderBySaveDateDesc(Pageable page);




}
