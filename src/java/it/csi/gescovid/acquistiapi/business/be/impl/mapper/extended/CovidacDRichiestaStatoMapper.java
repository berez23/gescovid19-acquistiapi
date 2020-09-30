package it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacDRichiestaStato;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.generated.BaseCovidacDRichiestaStatoMapper;

public interface CovidacDRichiestaStatoMapper extends BaseCovidacDRichiestaStatoMapper {

	@Select({ "select", "ric_stato_id, ric_stato_cod, ric_stato_desc, validita_inizio, validita_fine, ",
			"data_creazione, data_modifica, data_cancellazione, utente_operazione", 
			"from covidac_d_richiesta_stato",
			"where ric_stato_cod = #{ricStatoCod,jdbcType=VARCHAR}" })
	@Results({ @Result(column = "ric_stato_id", property = "ricStatoId", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "ric_stato_cod", property = "ricStatoCod", jdbcType = JdbcType.VARCHAR),
			@Result(column = "ric_stato_desc", property = "ricStatoDesc", jdbcType = JdbcType.VARCHAR),
			@Result(column = "validita_inizio", property = "validitaInizio", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "validita_fine", property = "validitaFine", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "data_creazione", property = "dataCreazione", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "data_modifica", property = "dataModifica", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "data_cancellazione", property = "dataCancellazione", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "utente_operazione", property = "utenteOperazione", jdbcType = JdbcType.VARCHAR) })
	CovidacDRichiestaStato selectByStatoCod(String ricStatoCod);
	
}
