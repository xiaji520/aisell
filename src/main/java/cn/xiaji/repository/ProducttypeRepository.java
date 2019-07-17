package cn.xiaji.repository;
import cn.xiaji.domain.Producttype;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProducttypeRepository extends BaseRepository<Producttype, Long> {
    //拿到数据
    @Query("select o from Producttype o where o.parent.id is not null")
    List<Producttype> findAllTypes();
}