package cn.xiaji.query;
import cn.xiaji.domain.Permission;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class PermissionQuery extends BaseQuery {
    //如果生成的表没有name，这里会报错，遇到这种情况请把它删除
    private String name;
    
    @Override
    public Specification createSpec() {
        Specification<Permission> build = Specifications.<Permission>and()
                .like(StringUtils.isNotBlank(name), "name", "%" + name + "%")
                .build();
        return build;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
}