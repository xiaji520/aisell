package cn.xiaji.repository;

import cn.xiaji.domain.Systemdictionarydetail;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SystemdictionarydetailRepository extends BaseRepository<Systemdictionarydetail, Long> {
    //根据类型拿相应数据
    @Query("select o from Systemdictionarydetail o where o.types.sn = ?1")
    List<Systemdictionarydetail> findAllBySn(String sn);
}