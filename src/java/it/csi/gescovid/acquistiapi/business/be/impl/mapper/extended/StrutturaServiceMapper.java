package it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.Struttura;

public interface StrutturaServiceMapper {

	@Select({
		"<script>",
		"select",
		"	str.id_struttura,",
		"	str.id_ente,",
		"	str.nome,",
		"	str.natura,",
		"	str.indirizzo,",
		"	str.comune_istat",
		"from",
		"	struttura str",
		"	inner join covidac_r_app_struttura rapp on str.id_struttura = rapp.id_struttura",
		"<if test='paramEnte != null'>where id_ente = #{paramEnte,jdbcType=INTEGER}</if>",
		"</script>"
	})
	@Results({
		@Result(column = "id_struttura", property = "idStruttura", jdbcType = JdbcType.VARCHAR, id = true),
		@Result(column = "id_ente", property = "idEnte", jdbcType = JdbcType.INTEGER),
		@Result(column = "nome", property = "nome", jdbcType = JdbcType.VARCHAR),
		@Result(column = "natura", property = "natura", jdbcType = JdbcType.VARCHAR),
		@Result(column = "indirizzo", property = "indirizzo", jdbcType = JdbcType.VARCHAR),
		@Result(column = "comune_istat", property = "comuneIstat", jdbcType = JdbcType.VARCHAR)
	})
	List<Struttura> selectElencoStruttureByIdEnte(@Param("paramEnte") Integer paramEnte);
	
}
