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

import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacDOrdineStato;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacRMagazProd;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacROrdineRicDet;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacROrdineStato;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTOrdine;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.extended.CountExt;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.extended.CovidacROrdineStatoExt;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.extended.CovidacTOrdineExt;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.generated.BaseCovidacTOrdineMapper;

public interface CovidacTOrdineMapper extends BaseCovidacTOrdineMapper {
	
	
	
	
	
	
	
	@Select({"<script>", 
		"select", "ricdet_stato_id, ord_id, ricdet_id, data_creazione, data_modifica, data_cancellazione, ",
		"utente_operazione", "from covidac_r_ordine_ric_det",
		"where ",
		"data_cancellazione is null",
	    "and ricdet_id in ",
		"  <foreach item='item' index='index' collection='list' ",
        "		open='(' separator=',' close=')'>",
        "			#{item} ",
        "		</foreach>",
		"</script>"})
	 @Results({
		    @Result(column="ord_id", property="ordId", jdbcType=JdbcType.INTEGER, id=true),
	        @Result(column="ord_identificativo", property="ordIdentificativo", jdbcType=JdbcType.VARCHAR),
	        @Result(column="quantita_ordinata", property="quantitaOrdinata", jdbcType=JdbcType.INTEGER),
	        @Result(column="ord_data_consegna_prevista", property="ordDataConsegnaPrevista", jdbcType=JdbcType.TIMESTAMP),
	        @Result(column="quantita_consegnata", property="quantitaConsegnata", jdbcType=JdbcType.INTEGER),
	        @Result(column="ord_data_consegna", property="ordDataConsegna", jdbcType=JdbcType.TIMESTAMP),	        
	        @Result(column="ord_data", property="ordData", jdbcType=JdbcType.TIMESTAMP),
	        @Result(column="data_creazione", property="dataCreazione", jdbcType=JdbcType.TIMESTAMP),
	        @Result(column="ord_stato_cod", property="ordStatoCod", jdbcType=JdbcType.VARCHAR),
	        @Result(column="ord_stato_desc", property="ordStatoDesc", jdbcType=JdbcType.VARCHAR),	
	        @Result(column="prod_cod", property="prodCod", jdbcType=JdbcType.VARCHAR),
	        @Result(column="prod_nome", property="prodNome", jdbcType=JdbcType.VARCHAR),
	        @Result(column="prod_desc", property="prodDesc", jdbcType=JdbcType.VARCHAR),
	        
	    })
	
	List<CovidacROrdineRicDet> selecDettRic(@Param("list") Integer[] list);
	
	
	@Select
			({"<script>", 
				"select", "count(*) as count ",
				" from covidac_t_richiesta_det ",
				"where data_cancellazione is null and prod_id= #{prodId,jdbcType=INTEGER}",
				"and ricdet_id in ",
				"  <foreach item='item' index='index' collection='list' ",
		        "		open='(' separator=',' close=')'>",
		        "			#{item} ",
		        "		</foreach>",
				"</script>"})
			 @Results({
				    @Result(column="count", property="count", jdbcType=JdbcType.INTEGER)
			        
			    })
			
	CountExt selecCountDettRic(@Param("list") Integer[] list, @Param("prodId") Integer prodId);
	
	 
	 @Select({"<script>",
	        "select",
	        "ord.ord_id, ord_identificativo, quantita_ordinata, ord_data_consegna_prevista, ord.quantita_consegnata, ord_data_consegna, ",
	        "ord_data, ord.data_creazione, stato.ord_stato_cod, stato.ord_stato_desc, prod.prod_cod, prod.prod_nome, prod.prod_desc",
	        "from covidac_t_ordine ord",
	        "inner join covidac_t_prod prod on (ord.prod_id = prod.prod_id) ",
			"inner join covidac_r_ordine_stato rstato on(ord.ord_id = rstato.ord_id )",
			"inner join covidac_d_ordine_stato stato on (stato.ord_stato_id = rstato.ord_stato_id)",
			"where",
			"prod.data_cancellazione is null",
			"and rstato.data_cancellazione is null",
			"and stato.data_cancellazione is null",
			"and ord.data_cancellazione is null",
			"and now() between rstato.validita_inizio and coalesce(rstato.validita_fine, to_timestamp('01/01/2999','dd/mm/yyyy'))",
			"<if test='stato != null'> and stato.ord_stato_cod =#{stato,jdbcType=VARCHAR} </if>",
			"</script>"
	    })
	 @Results({
		    @Result(column="ord_id", property="ordId", jdbcType=JdbcType.INTEGER, id=true),
	        @Result(column="ord_identificativo", property="ordIdentificativo", jdbcType=JdbcType.VARCHAR),
	        @Result(column="quantita_ordinata", property="quantitaOrdinata", jdbcType=JdbcType.INTEGER),
	        @Result(column="ord_data_consegna_prevista", property="ordDataConsegnaPrevista", jdbcType=JdbcType.TIMESTAMP),
	        @Result(column="quantita_consegnata", property="quantitaConsegnata", jdbcType=JdbcType.INTEGER),
	        @Result(column="ord_data_consegna", property="ordDataConsegna", jdbcType=JdbcType.TIMESTAMP),	        
	        @Result(column="ord_data", property="ordData", jdbcType=JdbcType.TIMESTAMP),
	        @Result(column="data_creazione", property="dataCreazione", jdbcType=JdbcType.TIMESTAMP),
	        @Result(column="ord_stato_cod", property="ordStatoCod", jdbcType=JdbcType.VARCHAR),
	        @Result(column="ord_stato_desc", property="ordStatoDesc", jdbcType=JdbcType.VARCHAR),	
	        @Result(column="prod_cod", property="prodCod", jdbcType=JdbcType.VARCHAR),
	        @Result(column="prod_nome", property="prodNome", jdbcType=JdbcType.VARCHAR),
	        @Result(column="prod_desc", property="prodDesc", jdbcType=JdbcType.VARCHAR),
	        
	    })
	
	 List<CovidacTOrdineExt> selectOrdini(@Param("stato") String stato);
	 
	 
	 
	 @Select({
	        "select",
	        "ord.ord_id, ord_identificativo, quantita_ordinata, ord_data_consegna_prevista, ord.quantita_consegnata, ord_data_consegna, ",
	        "ord_data, ord.data_creazione, stato.ord_stato_cod, stato.ord_stato_desc, prod.prod_cod, prod.prod_nome, prod.prod_desc",
	        "from covidac_t_ordine ord",
	        "inner join covidac_t_prod prod on (ord.prod_id = prod.prod_id) ",
			"inner join covidac_r_ordine_stato rstato on(ord.ord_id = rstato.ord_id )",
			"inner join covidac_d_ordine_stato stato on (stato.ord_stato_id = rstato.ord_stato_id)",
			"where",
			"now() between rstato.validita_inizio and coalesce(rstato.validita_fine, to_timestamp('01/01/2999','dd/mm/yyyy'))",
			"and ord.ord_id =#{ordId,jdbcType=INTEGER}",
			
	    })
	 @Results({
		    @Result(column="ord_id", property="ordId", jdbcType=JdbcType.INTEGER, id=true),
	        @Result(column="ord_identificativo", property="ordIdentificativo", jdbcType=JdbcType.VARCHAR),
	        @Result(column="quantita_ordinata", property="quantitaOrdinata", jdbcType=JdbcType.INTEGER),
	        @Result(column="ord_data_consegna_prevista", property="ordDataConsegnaPrevista", jdbcType=JdbcType.TIMESTAMP),
	        @Result(column="quantita_consegnata", property="quantitaConsegnata", jdbcType=JdbcType.INTEGER),
	        @Result(column="ord_data_consegna", property="ordDataConsegna", jdbcType=JdbcType.TIMESTAMP),	        
	        @Result(column="ord_data", property="ordData", jdbcType=JdbcType.TIMESTAMP),
	        @Result(column="data_creazione", property="dataCreazione", jdbcType=JdbcType.TIMESTAMP),
	        @Result(column="ord_stato_cod", property="ordStatoCod", jdbcType=JdbcType.VARCHAR),
	        @Result(column="ord_stato_desc", property="ordStatoDesc", jdbcType=JdbcType.VARCHAR),	
	        @Result(column="prod_cod", property="prodCod", jdbcType=JdbcType.VARCHAR),
	        @Result(column="prod_nome", property="prodNome", jdbcType=JdbcType.VARCHAR),
	        @Result(column="prod_desc", property="prodDesc", jdbcType=JdbcType.VARCHAR),
	        
	    })
	
	 CovidacTOrdineExt selectOrdiniById(@Param("ordId") Integer ordId);
	 
	 
	 @Select({"select",
	        "ord.ord_id, ord_identificativo, quantita_ordinata, ord_data_consegna_prevista, ord.quantita_consegnata, ord_data_consegna, ",
	        "ord_data, ord.data_creazione, stato.ord_stato_cod, stato.ord_stato_desc, prod.prod_cod, prod.prod_nome, prod.prod_desc",
	        "from covidac_t_ordine ord",
	        "inner join covidac_t_prod prod on (ord.prod_id = prod.prod_id) ",
			"inner join covidac_r_ordine_stato rstato on(ord.ord_id = rstato.ord_id )",
			"inner join covidac_d_ordine_stato stato on (stato.ord_stato_id = rstato.ord_stato_id)",
			"where",
			"prod.data_cancellazione is null",
			"and rstato.data_cancellazione is null",
			"and stato.data_cancellazione is null",
			"and now() between rstato.validita_inizio and coalesce(rstato.validita_fine, to_timestamp('01/01/2999','dd/mm/yyyy'))",
			"and ord.ord_id = #{ordId,jdbcType=INTEGER}"
	    })
	 @Results({
		    @Result(column="ord_id", property="ordId", jdbcType=JdbcType.INTEGER, id=true),
	        @Result(column="ord_identificativo", property="ordIdentificativo", jdbcType=JdbcType.VARCHAR),
	        @Result(column="quantita_ordinata", property="quantitaOrdinata", jdbcType=JdbcType.INTEGER),
	        @Result(column="ord_data_consegna_prevista", property="ordDataConsegnaPrevista", jdbcType=JdbcType.TIMESTAMP),
	        @Result(column="quantita_consegnata", property="quantitaConsegnata", jdbcType=JdbcType.INTEGER),
	        @Result(column="ord_data_consegna", property="ordDataConsegna", jdbcType=JdbcType.TIMESTAMP),	        
	        @Result(column="ord_data", property="ordData", jdbcType=JdbcType.TIMESTAMP),
	        @Result(column="data_creazione", property="dataCreazione", jdbcType=JdbcType.TIMESTAMP),
	        @Result(column="ord_stato_cod", property="ordStatoCod", jdbcType=JdbcType.VARCHAR),
	        @Result(column="ord_stato_desc", property="ordStatoDesc", jdbcType=JdbcType.VARCHAR),	
	        @Result(column="prod_cod", property="prodCod", jdbcType=JdbcType.VARCHAR),
	        @Result(column="prod_nome", property="prodNome", jdbcType=JdbcType.VARCHAR),
	        @Result(column="prod_desc", property="prodDesc", jdbcType=JdbcType.VARCHAR),
	        
	    })
	 CovidacTOrdineExt selectOrdineById(@Param("ordId") Integer ordId);	 
	 
	 
	   @Insert({
	        "insert into covidac_t_ordine (ord_identificativo, ord_data, ",
	        "ord_data_consegna_prevista,  ",
	        "prod_id, quantita_ordinata, ",
	        " data_creazione, ",
	        "data_modifica, data_cancellazione, ",
	        "utente_operazione)",
	        "values (#{ordIdentificativo,jdbcType=VARCHAR}, #{ordData,jdbcType=TIMESTAMP}, ",
	        "#{ordDataConsegnaPrevista,jdbcType=TIMESTAMP},  ",
	        "#{prodId,jdbcType=INTEGER}, #{quantitaOrdinata,jdbcType=INTEGER}, ",
	        " #{dataCreazione,jdbcType=TIMESTAMP}, ",
	        "#{dataModifica,jdbcType=TIMESTAMP}, ",
	        "#{utenteOperazione,jdbcType=VARCHAR})"
	    })
	    @Options(useGeneratedKeys=true,keyProperty="ordId")
	    int insertOrdineExt(CovidacTOrdineExt record);


    @Select({
    	"select",
    	"ordine.ord_id, ordine.ord_identificativo, ordine.ord_data, ordine.ord_data_consegna_prevista, ordine.ord_data_consegna,",
    	"ordine.prod_id, ordine.quantita_ordinata, ordine.quantita_consegnata, ordine.data_creazione, ordine.data_modifica,",
    	"ordine.data_cancellazione, ordine.utente_operazione",
    	"from covidac_t_ordine ordine, covidac_r_ordine_ric_det relazione, covidac_t_richiesta_det dettaglio",
    	"where dettaglio.ricdet_id = #{ricdetId,jdbcType=INTEGER}",
    	"and dettaglio.ricdet_id=relazione.ricdet_id",
    	"and relazione.ord_id=ordine.ord_id",
    })
    @Results({
        @Result(column="ord_id", property="ordId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ord_identificativo", property="ordIdentificativo", jdbcType=JdbcType.VARCHAR),
        @Result(column="ord_data", property="ordData", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="ord_data_consegna_prevista", property="ordDataConsegnaPrevista", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="ord_data_consegna", property="ordDataConsegna", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="prod_id", property="prodId", jdbcType=JdbcType.INTEGER),
        @Result(column="quantita_ordinata", property="quantitaOrdinata", jdbcType=JdbcType.INTEGER),
        @Result(column="quantita_consegnata", property="quantitaConsegnata", jdbcType=JdbcType.INTEGER),
        @Result(column="data_creazione", property="dataCreazione", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="data_modifica", property="dataModifica", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="data_cancellazione", property="dataCancellazione", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="utente_operazione", property="utenteOperazione", jdbcType=JdbcType.VARCHAR)
    })
    CovidacTOrdine selectByIdDettaglioRichiesta(Integer ricdetId);
    
    
	@Select({ "select", "ord_stato_id, ord_stato_cod, ord_stato_desc, validita_inizio, validita_fine, ",
		"data_creazione, data_modifica, data_cancellazione, utente_operazione", "from covidac_d_ordine_stato",
		"where ord_stato_cod = #{ordStatoCod,jdbcType=VARCHAR}",
		"and data_cancellazione is null"})
	@Results({ @Result(column = "ord_stato_id", property = "ordStatoId", jdbcType = JdbcType.INTEGER, id = true),
		@Result(column = "ord_stato_cod", property = "ordStatoCod", jdbcType = JdbcType.VARCHAR),
		@Result(column = "ord_stato_desc", property = "ordStatoDesc", jdbcType = JdbcType.VARCHAR),
		@Result(column = "validita_inizio", property = "validitaInizio", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "validita_fine", property = "validitaFine", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "data_creazione", property = "dataCreazione", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "data_modifica", property = "dataModifica", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "data_cancellazione", property = "dataCancellazione", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "utente_operazione", property = "utenteOperazione", jdbcType = JdbcType.VARCHAR) })
	CovidacDOrdineStato selectStatoByCodiceStato(String ordStatoCod);
	
	@Select({ "select",
		"ordrstato_id, ord_id, ord_stato_id, validita_inizio, validita_fine, data_creazione, ",
		"data_modifica, data_cancellazione, utente_operazione",
		"from covidac_r_ordine_stato",
		"where now() between validita_inizio and coalesce(validita_fine, to_timestamp('01/01/2999','dd/mm/yyyy')) ",
		"and data_cancellazione is null",
		 "and ord_id = #{ordId,jdbcType=INTEGER} " })
	@Results({ @Result(column = "ordrstato_id", property = "ordrstatoId", jdbcType = JdbcType.INTEGER, id = true),
		@Result(column = "ord_id", property = "ordId", jdbcType = JdbcType.INTEGER),
		@Result(column = "ord_stato_id", property = "ordStatoId", jdbcType = JdbcType.INTEGER),
		@Result(column = "validita_inizio", property = "validitaInizio", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "validita_fine", property = "validitaFine", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "data_creazione", property = "dataCreazione", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "data_modifica", property = "dataModifica", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "data_cancellazione", property = "dataCancellazione", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "utente_operazione", property = "utenteOperazione", jdbcType = JdbcType.VARCHAR) })
	CovidacROrdineStato selectROrdineStatoByIdOrdIdStato(@Param("ordId")Integer ordId);
	

	
	@Select({ "select", "prodmag_id, magaz_id, prod_id, quantita_disponibile, quantita_distribuita, validita_inizio, ",
		"validita_fine, data_creazione, data_modifica, data_cancellazione, utente_operazione",
		"from covidac_r_magaz_prod", "where prod_id = #{idProd,jdbcType=INTEGER}",
		"and now() between validita_inizio and coalesce(validita_fine, to_timestamp('01/01/2999','dd/mm/yyyy'))",
		"and data_cancellazione is null"})
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
CovidacRMagazProd selectRMagazProdByIdProd(@Param("idProd") Integer idProd);
	
	
	@Update({ "update covidac_r_ordine_stato", "set ord_id = #{ordId,jdbcType=INTEGER},",
		"ord_stato_id = #{ordStatoId,jdbcType=INTEGER},", "validita_inizio = #{validitaInizio,jdbcType=TIMESTAMP},",
		"validita_fine = ,",
		"data_creazione = #{dataCreazione,jdbcType=TIMESTAMP},",
		"data_modifica = #{dataModifica,jdbcType=TIMESTAMP},",
		"data_cancellazione = #{dataCancellazione,jdbcType=TIMESTAMP},",
		"utente_operazione = #{utenteOperazione,jdbcType=VARCHAR}",
		"where ordrstato_id = #{ordrstatoId,jdbcType=INTEGER}" })
	int updateByROrdineStatoDataFineNowPrimaryKey(CovidacROrdineStato record);
	
	
	@Insert({ "insert into covidac_r_ordine_stato (ord_id, ord_stato_id, ", "validita_inizio, validita_fine, ",
		"data_creazione, data_modifica, ", "data_cancellazione, utente_operazione)",
		"values (#{ordId,jdbcType=INTEGER}, #{ordStatoId,jdbcType=INTEGER}, ",
		"now(), #{validitaFine,jdbcType=TIMESTAMP}, ",
		"now(), now(), ",
		"#{dataCancellazione,jdbcType=TIMESTAMP}, #{utenteOperazione,jdbcType=VARCHAR})" })
	@Options(useGeneratedKeys = true, keyProperty = "ordrstatoId")
	int insertROrdineStatoNew(CovidacROrdineStato record);
    
	
	@Update({ "update covidac_r_ordine_stato", "set ord_id = #{ordId,jdbcType=INTEGER},",
		"ord_stato_id = #{ordStatoId,jdbcType=INTEGER},", "validita_inizio = #{validitaInizio,jdbcType=TIMESTAMP},",
		"validita_fine = (now() - interval '1 second'),",
		"data_creazione = #{dataCreazione,jdbcType=TIMESTAMP},",
		"data_modifica = #{dataModifica,jdbcType=TIMESTAMP},",
		"data_cancellazione = #{dataCancellazione,jdbcType=TIMESTAMP},",
		"utente_operazione = #{utenteOperazione,jdbcType=VARCHAR}",
		"where ordrstato_id = #{ordrstatoId,jdbcType=INTEGER}" })
	int updateROrdineStatoDataFineNow(CovidacROrdineStato record);
	
	
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table covidac_r_ordine_stato
	 * @mbg.generated
	 */
	@Insert({ "insert into covidac_r_ordine_stato (ord_id, ord_stato_id, ", "validita_inizio, validita_fine, ",
			"data_creazione, data_modifica, ", "data_cancellazione, ",
			"data_consegna, quantita_consegnata, ", // 02-05-2020 modifica per gestire lo stato di consegna parziale
			"utente_operazione)",
			"values (#{ordId,jdbcType=INTEGER}, #{ordStatoId,jdbcType=INTEGER}, ",
			"now(), #{validitaFine,jdbcType=TIMESTAMP}, ",
			"now(), now(), ",
			"#{dataCancellazione,jdbcType=TIMESTAMP}, ",
			"#{dataConsegna,jdbcType=TIMESTAMP}, #{quantitaConsegnata,jdbcType=INTEGER},", // 02-05-2020 modifica per gestire lo stato di consegna parziale
			"#{utenteOperazione,jdbcType=VARCHAR})" })
	@Options(useGeneratedKeys = true, keyProperty = "ordrstatoId")
	int insertROrdineStatoValiditaInizioNow(CovidacROrdineStato record);
	
	
	
	@Update({ "update covidac_r_magaz_prod", "set magaz_id = #{magazId,jdbcType=INTEGER},",
		"prod_id = #{prodId,jdbcType=INTEGER},", "quantita_disponibile = #{quantitaDisponibile,jdbcType=INTEGER},",
		"quantita_distribuita = #{quantitaDistribuita,jdbcType=INTEGER},",
		"validita_inizio = #{validitaInizio,jdbcType=TIMESTAMP},",
		"validita_fine = (now() - interval '1 second'),",
		"data_creazione = #{dataCreazione,jdbcType=TIMESTAMP},",
		"data_modifica = #{dataModifica,jdbcType=TIMESTAMP},",
		"data_cancellazione = #{dataCancellazione,jdbcType=TIMESTAMP},",
		"utente_operazione = #{utenteOperazione,jdbcType=VARCHAR}",
		"where prodmag_id = #{prodmagId,jdbcType=INTEGER}" })
	int updateRMagazProdDataFineNow(CovidacRMagazProd record);
	
	@Insert({ "insert into covidac_r_magaz_prod (magaz_id, prod_id, ", "quantita_disponibile, quantita_distribuita, ",
		"validita_inizio, validita_fine, ", "data_creazione, data_modifica, ",
		"data_cancellazione, utente_operazione)",
		"values (#{magazId,jdbcType=INTEGER}, #{prodId,jdbcType=INTEGER}, ",
		"#{quantitaDisponibile,jdbcType=INTEGER}, #{quantitaDistribuita,jdbcType=INTEGER}, ",
		"now(), #{validitaFine,jdbcType=TIMESTAMP}, ",
		"now(), #{dataModifica,jdbcType=TIMESTAMP}, ",
		"#{dataCancellazione,jdbcType=TIMESTAMP}, #{utenteOperazione,jdbcType=VARCHAR})" })
	@Options(useGeneratedKeys = true, keyProperty = "prodmagId")
	int insertRMagazProdValiditaInizioNow(CovidacRMagazProd record);
	
	
	@Update({ "update covidac_t_ordine", 
		"set quantita_ordinata = #{quantitaOrdinata,jdbcType=INTEGER},",
		"data_modifica = now(),",
		"utente_operazione = #{utenteOperazione,jdbcType=VARCHAR}", "where ord_id = #{ordId,jdbcType=INTEGER}" })
	int updateQuantitaOrdine(CovidacTOrdine record);
	
	
	@Select({ "select", "ord_id, ord_identificativo, ord_data, ord_data_consegna_prevista, ord_data_consegna, ",
		"prod_id, quantita_ordinata, quantita_consegnata, data_creazione, data_modifica, ",
		"data_cancellazione, utente_operazione", "from covidac_t_ordine",
		"where data_cancellazione is null and prod_id = #{prodId,jdbcType=INTEGER}",
	"and ord_identificativo = #{ordIdentificativo,jdbcType=VARCHAR}" })
	@Results({ @Result(column = "ord_id", property = "ordId", jdbcType = JdbcType.INTEGER, id = true),
		@Result(column = "ord_identificativo", property = "ordIdentificativo", jdbcType = JdbcType.VARCHAR),
		@Result(column = "ord_data", property = "ordData", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "ord_data_consegna_prevista", property = "ordDataConsegnaPrevista", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "ord_data_consegna", property = "ordDataConsegna", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "prod_id", property = "prodId", jdbcType = JdbcType.INTEGER),
		@Result(column = "quantita_ordinata", property = "quantitaOrdinata", jdbcType = JdbcType.INTEGER),
		@Result(column = "quantita_consegnata", property = "quantitaConsegnata", jdbcType = JdbcType.INTEGER),
		@Result(column = "data_creazione", property = "dataCreazione", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "data_modifica", property = "dataModifica", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "data_cancellazione", property = "dataCancellazione", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "utente_operazione", property = "utenteOperazione", jdbcType = JdbcType.VARCHAR) })
	CovidacTOrdine selectOrdineByCodProdEIdentificativo(@Param("prodId")Integer prodId, @Param("ordIdentificativo") String ordIdentificativo);

	@Update({
		"update covidac_t_ordine",
		"set",
		"	data_cancellazione = now(),",
		"	utente_operazione = #{utenteOperazione,jdbcType=VARCHAR}",
		"where ord_id = #{ordId,jdbcType=INTEGER}"
	})
	int deleteOrdine(CovidacTOrdine record);
	
	 @Select({
		 "select",
		 "	ordine.ord_id,",
		 "	ordine.ord_identificativo,",
		 "	ordine.ord_data,",
		 "	ordine.ord_data_consegna_prevista,",
		 "	ordine.ord_data_consegna,",
		 "	ordine.prod_id,",
		 "	ordine.quantita_ordinata,",
		 "	ordine.quantita_consegnata,",
		 "	ordine.data_creazione,",
		 "	ordine.data_modifica,",
		 "	ordine.data_cancellazione,",
		 "	ordine.utente_operazione,",
		 "	prod.prod_cod,",
		 "	prod.prod_nome,",
		 "	prod.prod_desc,",
		 "	stato.ord_stato_cod,",
		 "	stato.ord_stato_desc",
		 "from",
		 "	covidac_t_ordine ordine",
		 "	inner join covidac_t_prod prod on ordine.prod_id = prod.prod_id",
		 "	inner join covidac_r_ordine_stato rstato on ordine.ord_id = rstato.ord_id",
		 "	and now() between rstato.validita_inizio and coalesce(rstato.validita_fine, to_timestamp('01/01/2999','dd/mm/yyyy'))",
		 "	and rstato.data_cancellazione  is null",
		 "	inner join covidac_d_ordine_stato stato on stato.ord_stato_id = rstato.ord_stato_id",
		 "	and stato.data_cancellazione  is null",
		 "where",
		 "	ordine.ord_id = #{ordId,jdbcType=INTEGER}"
	})
	@Results({
		@Result(column = "ord_id", property = "ordId", jdbcType = JdbcType.INTEGER, id = true),
		@Result(column = "ord_identificativo", property = "ordIdentificativo", jdbcType = JdbcType.VARCHAR),
		@Result(column = "ord_data", property = "ordData", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "ord_data_consegna_prevista", property = "ordDataConsegnaPrevista", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "ord_data_consegna", property = "ordDataConsegna", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "prod_id", property = "prodId", jdbcType = JdbcType.INTEGER),
		@Result(column = "quantita_ordinata", property = "quantitaOrdinata", jdbcType = JdbcType.INTEGER),
		@Result(column = "quantita_consegnata", property = "quantitaConsegnata", jdbcType = JdbcType.INTEGER),
		@Result(column = "data_creazione", property = "dataCreazione", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "data_modifica", property = "dataModifica", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "data_cancellazione", property = "dataCancellazione", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "utente_operazione", property = "utenteOperazione", jdbcType = JdbcType.VARCHAR),
	    @Result(column = "prod_cod", property="prodCod", jdbcType=JdbcType.VARCHAR),
	    @Result(column = "prod_nome", property="prodNome", jdbcType=JdbcType.VARCHAR),
	    @Result(column = "prod_desc", property="prodDesc", jdbcType=JdbcType.VARCHAR),
	    @Result(column = "ord_stato_cod", property="ordStatoCod", jdbcType=JdbcType.VARCHAR),
	    @Result(column = "ord_stato_desc", property="ordStatoDesc", jdbcType=JdbcType.VARCHAR)
	})
	CovidacTOrdineExt selectDettaglioOrdineById(@Param("ordId") Integer ordId);
	 
	 @Select({
		 "select",
		 "	rstato.ordrstato_id,",
		 "	rstato.ord_id,",
		 "	rstato.data_creazione,",
		 "	rstato.quantita_consegnata,",
		 "	rstato.data_consegna,",
		 "	stato.ord_stato_cod,",
		 "	stato.ord_stato_desc",
		 "from",
		 "	covidac_r_ordine_stato rstato",
		 "	inner join covidac_d_ordine_stato stato on rstato.ord_stato_id = stato.ord_stato_id",
		 "where",
		 "	rstato.ord_id = #{ordId,jdbcType=INTEGER}",
		 "order by rstato.ordrstato_id desc"
    })
	@Results({
		@Result(column = "ordrstato_id", property = "ordrstatoId", jdbcType = JdbcType.INTEGER, id = true),
		@Result(column = "ord_id", property = "ordId", jdbcType = JdbcType.INTEGER),
		@Result(column = "data_creazione", property="dataCreazione", jdbcType=JdbcType.TIMESTAMP),
		@Result(column = "quantita_consegnata", property="quantitaConsegnata", jdbcType=JdbcType.INTEGER),
		@Result(column = "data_consegna", property="dataConsegna", jdbcType=JdbcType.TIMESTAMP),
        @Result(column = "ord_stato_cod", property="ordStatoCod", jdbcType=JdbcType.VARCHAR),
        @Result(column = "ord_stato_desc", property="ordStatoDesc", jdbcType=JdbcType.VARCHAR)
    })
	List<CovidacROrdineStatoExt> selectCronologiaOrdineById(@Param("ordId") Integer ordId);	 

	 @Update({ "update covidac_t_ordine", "set ord_identificativo = #{ordIdentificativo,jdbcType=VARCHAR},",
			"ord_data = #{ordData,jdbcType=TIMESTAMP},",
			"ord_data_consegna_prevista = #{ordDataConsegnaPrevista,jdbcType=TIMESTAMP},",
			"ord_data_consegna = #{ordDataConsegna,jdbcType=TIMESTAMP},", "prod_id = #{prodId,jdbcType=INTEGER},",
			"quantita_ordinata = #{quantitaOrdinata,jdbcType=INTEGER},",
			"quantita_consegnata = #{quantitaConsegnata,jdbcType=INTEGER},",
			"data_creazione = #{dataCreazione,jdbcType=TIMESTAMP},",
			"data_modifica = now(),",
			"data_cancellazione = #{dataCancellazione,jdbcType=TIMESTAMP},",
			"utente_operazione = #{utenteOperazione,jdbcType=VARCHAR}", "where ord_id = #{ordId,jdbcType=INTEGER}" })
	 int updateOrdineByPrimaryKey(CovidacTOrdine record);
}
