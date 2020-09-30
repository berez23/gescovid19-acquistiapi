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

import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacRMagazProd;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTMagazEntrata;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTMagazzino;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.custom.CovidacTMagazEntrataCustom;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.extended.CovidacTMagazExt;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.generated.BaseCovidacTMagazzinoMapper;

public interface MagazzinoServiceMapper extends BaseCovidacTMagazzinoMapper {
	
	

	
/*	
 * query in prod il 05/04/2020
 * @Select({ "select", "mag.magaz_id, mag.magaz_nome, mag.magaz_indirizzo, mag.magaz_comune_codice, ",
		 "prod.prod_cod, prod.prod_nome, prod.prod_desc, rmagaz.quantita_disponibile," + 
		 "rmagaz.quantita_distribuita,", 
		 "sum (entr.quantita) quantita ",
		"from covidac_r_magaz_prod rmagaz, covidac_t_prod prod, covidac_t_magaz_entrata entr,", 
		"covidac_t_magazzino mag ",
		"where prod.prod_id = rmagaz.prod_id",
		"and entr.prod_id=prod.prod_id and ",
		"now() between rmagaz.validita_inizio and coalesce(rmagaz.validita_fine, to_timestamp('01/01/2999','dd/mm/yyyy'))",
		"and rmagaz.data_cancellazione is null",
		"and prod.data_cancellazione is null",
		"and entr.data_cancellazione is null",
		"and mag.magaz_id=rmagaz.magaz_id ",
		"and mag.data_cancellazione is null",
		"group by mag.magaz_id, mag.magaz_nome, mag.magaz_comune_codice, mag.magaz_indirizzo,",
		"prod.prod_cod, prod.prod_nome, prod.prod_desc, rmagaz.quantita_disponibile, rmagaz.quantita_distribuita"
		})*/
	
/*	
 * query fatta da me mettendo in outhe join la t_magaz_entrata (in quanto non necessario che i sia un ordine)
 * @Select({"select", 
		"mag.magaz_id, mag.magaz_nome, mag.magaz_indirizzo, mag.magaz_comune_codice,", 
		"prod.prod_cod, prod.prod_nome, prod.prod_desc, rmagaz.quantita_disponibile," , 
		"rmagaz.quantita_distribuita,",
		"sum (entr.quantita) quantita", 
		"from covidac_r_magaz_prod rmagaz", 
		"inner join covidac_t_magazzino mag on (rmagaz.magaz_id = mag.magaz_id)",
		"inner join covidac_t_prod prod on (prod.prod_id = rmagaz.prod_id)",
		"left join covidac_t_magaz_entrata entr on (prod.prod_id=entr.prod_id)", 
		"where", 
		"now() between rmagaz.validita_inizio and coalesce(rmagaz.validita_fine, to_timestamp('01/01/2999','dd/mm/yyyy'))",
		"and rmagaz.data_cancellazione is null",
		"and prod.data_cancellazione is null",
		"and entr.data_cancellazione is null",
		"and mag.magaz_id=rmagaz.magaz_id", 
		"and mag.data_cancellazione is null",
		"group by mag.magaz_id, mag.magaz_nome, mag.magaz_comune_codice, mag.magaz_indirizzo,",
		"prod.prod_cod, prod.prod_nome, prod.prod_desc, rmagaz.quantita_disponibile, rmagaz.quantita_distribuita",
		"order by prod_cod desc"
	})*/
	
	//query fatta da Giuliano
	@Select({"select", 
	"c.magaz_id,c.magaz_nome,c.magaz_indirizzo,c.magaz_comune_codice,",
	"a.prod_cod, a.prod_nome, a.prod_desc,",
	"coalesce (b.quantita_disponibile, 0) quantita_disponibile,",
	"coalesce (b.quantita_distribuita, 0) quantita_distribuita",
	",coalesce (sum (d.quantita),0) quantita",
	"from",
	"covidac_t_prod a",
	"left join covidac_r_magaz_prod b on",
	"b.prod_id=a.prod_id",
	"and",
	"now() between b.validita_inizio and coalesce(b.validita_fine, to_timestamp('01/01/2999','dd/mm/yyyy'))",
	"left join covidac_t_magazzino c",
	"on c.magaz_id=b.magaz_id",
	"left join covidac_t_magaz_entrata d",
	"on d.magaz_id=c.magaz_id",
	"and d.prod_id=a.prod_id",
	"where d.data_cancellazione is null and c.data_cancellazione is null",
	"and b.data_cancellazione is null and a.data_cancellazione is null",
	"group by 1,2,3,4,5,6,7,8,9",
	"order by 1,5"})
	@Results({ @Result(column = "magaz_id", property = "magazId", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "magaz_nome", property = "magazNome", jdbcType = JdbcType.VARCHAR),
			@Result(column = "magaz_indirizzo", property = "magazIndirizzo", jdbcType = JdbcType.VARCHAR),
			@Result(column = "magaz_comune_codice", property = "magazComuneCodice", jdbcType = JdbcType.VARCHAR),
			@Result(column="prod_cod", property="prodCod", jdbcType=JdbcType.VARCHAR),
		    @Result(column="prod_nome", property="prodNome", jdbcType=JdbcType.VARCHAR),
		    @Result(column="prod_desc", property="prodDesc", jdbcType=JdbcType.VARCHAR),
			@Result(column = "quantita_disponibile", property = "quantitaDisponibile", jdbcType = JdbcType.INTEGER),
			@Result(column = "quantita_distribuita", property = "quantitaDistribuita", jdbcType = JdbcType.INTEGER),
			@Result(column = "quantita", property = "quantita", jdbcType = JdbcType.INTEGER)
			 })
	List<CovidacTMagazExt> selectDisponibilitaMagazzino();
	
	
	
	@Select({ "select", 
		"c.magaz_id,c.magaz_nome,c.magaz_indirizzo,c.magaz_comune_codice,",
		"a.prod_cod, a.prod_nome, a.prod_desc,",
		"coalesce (b.quantita_disponibile, 0) quantita_disponibile,",
		"coalesce (b.quantita_distribuita, 0) quantita_distribuita",
		",coalesce (sum (d.quantita),0) quantita",
		"from",
		"covidac_t_prod a",
		"left join covidac_r_magaz_prod b on",
		"b.prod_id=a.prod_id",
		"and",
		"now() between b.validita_inizio and coalesce(b.validita_fine, to_timestamp('01/01/2999','dd/mm/yyyy'))",
		"left join covidac_t_magazzino c",
		"on c.magaz_id=b.magaz_id",
		"left join covidac_t_magaz_entrata d",
		"on d.magaz_id=c.magaz_id",
		"and d.prod_id=a.prod_id",
		"where d.data_cancellazione is null and c.data_cancellazione is null",
		"and b.data_cancellazione is null and a.data_cancellazione is null",
		"and a.prod_id =  #{prodId,jdbcType=INTEGER}",
		"group by 1,2,3,4,5,6,7,8,9",
		})
	@Results({ @Result(column = "magaz_id", property = "magazId", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "magaz_nome", property = "magazNome", jdbcType = JdbcType.VARCHAR),
			@Result(column = "magaz_indirizzo", property = "magazIndirizzo", jdbcType = JdbcType.VARCHAR),
			@Result(column = "magaz_comune_codice", property = "magazComuneCodice", jdbcType = JdbcType.VARCHAR),
			@Result(column="prod_cod", property="prodCod", jdbcType=JdbcType.VARCHAR),
		    @Result(column="prod_nome", property="prodNome", jdbcType=JdbcType.VARCHAR),
		    @Result(column="prod_desc", property="prodDesc", jdbcType=JdbcType.VARCHAR),
			@Result(column = "quantita_disponibile", property = "quantitaDisponibile", jdbcType = JdbcType.INTEGER),
			@Result(column = "quantita_distribuita", property = "quantitaDistribuita", jdbcType = JdbcType.INTEGER),
			@Result(column = "quantita", property = "quantita", jdbcType = JdbcType.INTEGER)
			 })
	List<CovidacTMagazExt> selectDisponibilitaMagazzinoPerProdId(@Param("prodId") Integer prodId);
	
	
	@Select({ "select", 
		"mag.prodmag_id, mag.magaz_id, mag.prod_id, mag.quantita_disponibile, mag.quantita_distribuita, mag.validita_inizio, ",
		"mag.validita_fine, mag.data_creazione, mag.data_modifica, mag.data_cancellazione, mag.utente_operazione",
		"from covidac_r_magaz_prod mag",
		"inner join covidac_t_prod prod on(mag.prod_id = prod.prod_id)",
		"where now() between mag.validita_inizio and coalesce(mag.validita_fine, to_timestamp('01/01/2999','dd/mm/yyyy'))",
		"and prod.prod_cod  = #{prodCod,jdbcType=VARCHAR}" })
	@Results({ @Result(column = "prodmag_id", property = "prodmagId", jdbcType = JdbcType.INTEGER, id = true),
		@Result(column = "magaz_id", property = "magazId", jdbcType = JdbcType.INTEGER),
		@Result(column = "prod_id", property = "prodId", jdbcType = JdbcType.INTEGER),
		@Result(column = "quantita_disponibile", property = "quantitaDisponibile", jdbcType = JdbcType.INTEGER),
		@Result(column = "quantita_distribuita", property = "quantitaDistribuita", jdbcType = JdbcType.INTEGER),
		@Result(column = "validita_inizio", property = "validitaInizio", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "validita_fine", property = "validitaFine", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "data_creazione", property = "dataCreazione", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "data_modifica", property = "dataModifica", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "data_cancellazione", property = "dataCancellazione", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "utente_operazione", property = "utenteOperazione", jdbcType = JdbcType.VARCHAR) })
	CovidacRMagazProd selectRMagazFromCodiceProdotto(@Param("prodCod") String prodCod);
	
	
	@Select({ "select", "magaz_id, magaz_nome, magaz_indirizzo, magaz_comune_codice, validita_inizio, ",
		"validita_fine, data_creazione, data_modifica, data_cancellazione, utente_operazione",
		"from covidac_t_magazzino",
		"where data_cancellazione is null"})
	@Results({ @Result(column = "magaz_id", property = "magazId", jdbcType = JdbcType.INTEGER, id = true),
		@Result(column = "magaz_nome", property = "magazNome", jdbcType = JdbcType.VARCHAR),
		@Result(column = "magaz_indirizzo", property = "magazIndirizzo", jdbcType = JdbcType.VARCHAR),
		@Result(column = "magaz_comune_codice", property = "magazComuneCodice", jdbcType = JdbcType.VARCHAR),
		@Result(column = "validita_inizio", property = "validitaInizio", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "validita_fine", property = "validitaFine", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "data_creazione", property = "dataCreazione", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "data_modifica", property = "dataModifica", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "data_cancellazione", property = "dataCancellazione", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "utente_operazione", property = "utenteOperazione", jdbcType = JdbcType.VARCHAR) })
	List<CovidacTMagazzino> selectMagazzinoDataCancellazioneNull();
	
//	@Insert({
//		"insert into covidac_t_magaz_entrata (magaz_id, prod_id, ",
//		"quantita, data_registrazione, ",
//		"data_creazione, data_modifica, ",
//		"utente_operazione)",
//		"values (#{parMagazId,jdbcType=INTEGER}, #{parProdId,jdbcType=INTEGER}, ",
//		"#{parQuantita,jdbcType=INTEGER}, '${parDataRegistrazione}'::date+date_trunc('sec', now() )::time, ",
//		"now(), now(), ",
//		"#{parUtenteOperazione,jdbcType=VARCHAR})" })
//	@Options(useGeneratedKeys = true, keyProperty = "mageId")
//	int insertMagazzino(@Param("record") CovidacTMagazEntrata record,
//			@Param("parMagazId") Integer parMagazId, @Param("parProdId") Integer parProdId, 
//			@Param("parQuantita") Integer parQuantita, @Param("parDataRegistrazione")String parDataRegistrazione, 
//			@Param("parUtenteOperazione") String parUtenteOperazione);
	
	@Insert({ "insert into covidac_t_magaz_entrata (magaz_id, prod_id, ", "quantita, data_registrazione, ",
		"data_creazione, data_modifica, ", "data_cancellazione, utente_operazione)",
		"values (#{magazId,jdbcType=INTEGER}, #{prodId,jdbcType=INTEGER}, ",
		"#{quantita,jdbcType=INTEGER}, '${parDataRegistrazione}'::date+date_trunc('sec', now() )::time, ",
		"now(), now(), ",
		"#{dataCancellazione,jdbcType=TIMESTAMP}, #{utenteOperazione,jdbcType=VARCHAR})" })
	@Options(useGeneratedKeys = true, keyProperty = "mageId")
	int insertMagazzino(CovidacTMagazEntrataCustom record);
	
}
