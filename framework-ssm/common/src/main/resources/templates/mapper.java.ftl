package ${package.Mapper};

import ${package.Entity}.${entity};
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* ${table.comment!} Dao接口
*/
@Repository
public interface ${table.mapperName} extends BaseMapper<${entity}> {

}
