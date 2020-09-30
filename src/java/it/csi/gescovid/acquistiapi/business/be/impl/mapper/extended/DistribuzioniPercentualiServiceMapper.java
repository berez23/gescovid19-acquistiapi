package it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacCPercDist;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.extended.PercDistExt;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.generated.BaseCovidacCPercDistMapper;

public interface DistribuzioniPercentualiServiceMapper extends BaseCovidacCPercDistMapper {
	
	
	/*
	 * SELECT pd_id, cperc.id_ente, pd_percentuale_distribuzione, pd_algoritmo_distribuzione, 
       validita_inizio, validita_fine, data_creazione, data_modifica, 
       data_cancellazione, utente_operazione, cperc.id_struttura, ente.nome as nomeente--, str.nome as nomestruttura 
  FROM covidac_c_perc_dist cperc
  inner join ente ente on (cperc.id_ente = ente.id_ente)
  left join struttura str on (cperc.id_struttura = str.id_struttura)
  where cperc.data_cancellazione is null
  and  now() between cperc.validita_inizio and coalesce(cperc.validita_fine, to_timestamp('01/01/2999','dd/mm/yyyy'));
	 */
	
	
	@Select({ "select", "pd_id, cperc.id_ente, pd_percentuale_distribuzione, pd_algoritmo_distribuzione, cperc.validita_inizio, ",
		"cperc.validita_fine, cperc.data_creazione, cperc.data_modifica, cperc.data_cancellazione, cperc.utente_operazione,",
		"cperc.id_struttura, ente.nome as nome_ente, str.nome as nome_struttura, natura",
		"from covidac_c_perc_dist cperc",
		"inner join ente ente on (cperc.id_ente = ente.id_ente)",
		"left join struttura str on (cperc.id_struttura = str.id_struttura)",
		"where cperc.data_cancellazione is null",
	"and  now() between cperc.validita_inizio and coalesce(cperc.validita_fine, to_timestamp('01/01/2999','dd/mm/yyyy'))"})
	@Results({ @Result(column = "pd_id", property = "pdId", jdbcType = JdbcType.INTEGER, id = true),
		@Result(column = "id_ente", property = "idEnte", jdbcType = JdbcType.INTEGER),
		@Result(column = "pd_percentuale_distribuzione", property = "pdPercentualeDistribuzione", jdbcType = JdbcType.NUMERIC),
		@Result(column = "pd_algoritmo_distribuzione", property = "pdAlgoritmoDistribuzione", jdbcType = JdbcType.VARCHAR),
		@Result(column = "validita_inizio", property = "validitaInizio", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "validita_fine", property = "validitaFine", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "data_creazione", property = "dataCreazione", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "data_modifica", property = "dataModifica", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "data_cancellazione", property = "dataCancellazione", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "utente_operazione", property = "utenteOperazione", jdbcType = JdbcType.VARCHAR),
		@Result(column = "id_struttura", property = "idStruttura", jdbcType = JdbcType.VARCHAR),
		@Result(column = "nome_ente", property = "nomeEnte", jdbcType = JdbcType.VARCHAR),
		@Result(column = "nome_struttura", property = "nomeStruttura", jdbcType = JdbcType.VARCHAR),
		@Result(column = "natura", property = "natura", jdbcType = JdbcType.VARCHAR)})
	List<PercDistExt> selectAllPercDist();
	
	@Select({ "select", "pd_id, cperc.id_ente, pd_percentuale_distribuzione, pd_algoritmo_distribuzione, cperc.validita_inizio, ",
		"cperc.validita_fine, cperc.data_creazione, cperc.data_modifica, cperc.data_cancellazione, cperc.utente_operazione,",
		"cperc.id_struttura, ente.nome as nome_ente, str.nome as nome_struttura, natura",
		"from covidac_c_perc_dist cperc",
		"inner join ente ente on (cperc.id_ente = ente.id_ente)",
		"left join struttura str on (cperc.id_struttura = str.id_struttura)",
		"where cperc.data_cancellazione is null",
		"and  now() between cperc.validita_inizio and coalesce(cperc.validita_fine, to_timestamp('01/01/2999','dd/mm/yyyy'))",
		"and pd_id = #{idPercentuale,jdbcType=INTEGER}"})
	@Results({ @Result(column = "pd_id", property = "pdId", jdbcType = JdbcType.INTEGER, id = true),
		@Result(column = "id_ente", property = "idEnte", jdbcType = JdbcType.INTEGER),
		@Result(column = "pd_percentuale_distribuzione", property = "pdPercentualeDistribuzione", jdbcType = JdbcType.NUMERIC),
		@Result(column = "pd_algoritmo_distribuzione", property = "pdAlgoritmoDistribuzione", jdbcType = JdbcType.VARCHAR),
		@Result(column = "validita_inizio", property = "validitaInizio", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "validita_fine", property = "validitaFine", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "data_creazione", property = "dataCreazione", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "data_modifica", property = "dataModifica", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "data_cancellazione", property = "dataCancellazione", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "utente_operazione", property = "utenteOperazione", jdbcType = JdbcType.VARCHAR),
		@Result(column = "id_struttura", property = "idStruttura", jdbcType = JdbcType.VARCHAR),
		@Result(column = "nome_ente", property = "nomeEnte", jdbcType = JdbcType.VARCHAR),
		@Result(column = "nome_struttura", property = "nomeStruttura", jdbcType = JdbcType.VARCHAR),
		@Result(column = "natura", property = "natura", jdbcType = JdbcType.VARCHAR)})
	PercDistExt selectPercDistPerIdPerc(@Param("idPercentuale") Integer idPercentuale);
	
	
	@Insert({ "insert into covidac_c_perc_dist (id_ente, pd_percentuale_distribuzione, ",
		"pd_algoritmo_distribuzione, validita_inizio, ", "validita_fine, data_creazione, ",
		"data_modifica, data_cancellazione, ", "utente_operazione, id_struttura)",
		"values (#{idEnte,jdbcType=INTEGER}, #{pdPercentualeDistribuzione,jdbcType=NUMERIC}, ",
		"#{pdAlgoritmoDistribuzione,jdbcType=VARCHAR}, now(), ",
		"#{validitaFine,jdbcType=TIMESTAMP}, now(), ",
		"now(), #{dataCancellazione,jdbcType=TIMESTAMP}, ",
		"#{utenteOperazione,jdbcType=VARCHAR}, #{idStruttura,jdbcType=VARCHAR})" })
	@Options(useGeneratedKeys = true, keyProperty = "pdId")
	int insertConDataInizioNow(CovidacCPercDist record);
	
	
	@Update({ "update covidac_c_perc_dist", "set id_ente = #{idEnte,jdbcType=INTEGER},",
		"pd_percentuale_distribuzione = #{pdPercentualeDistribuzione,jdbcType=NUMERIC},",
		"pd_algoritmo_distribuzione = #{pdAlgoritmoDistribuzione,jdbcType=VARCHAR},",
		"validita_fine = (now() - interval '1 second'),",
		"data_creazione = #{dataCreazione,jdbcType=TIMESTAMP},",
		"data_modifica = now(),",
		"data_cancellazione = #{dataCancellazione,jdbcType=TIMESTAMP},",
		"utente_operazione = #{utenteOperazione,jdbcType=VARCHAR},",
		"id_struttura = #{idStruttura,jdbcType=VARCHAR}", "where pd_id = #{pdId,jdbcType=INTEGER}" })
	int updateDataFineNow(CovidacCPercDist record);
	
}
