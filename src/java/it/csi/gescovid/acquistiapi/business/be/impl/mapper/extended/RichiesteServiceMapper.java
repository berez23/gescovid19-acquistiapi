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
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacDRichiestaStato;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacRMagazProd;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacRRichiestaDetStato;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTMagazUscita;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTMagazzino;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTOrdine;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTProd;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTRichiesta;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTRichiestaDet;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.Ente;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.custom.CovidacTRichiestaDetCustom;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.extended.EnteExt;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.generated.BaseCovidacTRichiestaDetMapper;

public interface RichiesteServiceMapper extends BaseCovidacTRichiestaDetMapper {
	
//	@Select({ "select", 
//			  "a.id_ente, a.nome, b.pd_percentuale_distribuzione, b.pd_algoritmo_distribuzione", 
//			  "from ente a, covidac_c_perc_dist b", 
//			  "where a.id_ente = #{idEnte,jdbcType=INTEGER}",
//			  "and a.id_ente = b.id_ente",
//			  "and b.data_cancellazione is null"
//			  //"and now() between b.validita_inizio and coalesce (b.validita_fine, to_timestamp('01/01/2999', 'dd/mm/yyyy'))",
//	})
//	@Results({
//			@Result(column = "id_ente", property = "idEnte", jdbcType = JdbcType.INTEGER, id = true),
//			@Result(column = "nome", property = "nome", jdbcType = JdbcType.VARCHAR),
//			@Result(column = "pd_percentuale_distribuzione", property = "pdPercentualeDistribuzione", jdbcType = JdbcType.VARCHAR),
//			@Result(column = "pd_algoritmo_distribuzione", property = "pdAlgoritmoDistribuzione", jdbcType = JdbcType.VARCHAR),
//			@Result(column = "tot_posti_target", property = "totPostiTarget", jdbcType = JdbcType.INTEGER)
//	})
//	EnteExt selectEnteByIdEnte(Integer idEnte);

	@Select({ "select", "id_ente, nome, tot_posti_target", "from ente", "where id_ente = #{idEnte,jdbcType=INTEGER}" })
	@Results({ @Result(column = "id_ente", property = "idEnte", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "nome", property = "nome", jdbcType = JdbcType.VARCHAR),
			@Result(column = "tot_posti_target", property = "totPostiTarget", jdbcType = JdbcType.INTEGER) })
	Ente selectEnteByIdEnte(Integer idEnte);
	
	@Select({ 
		"select",
		"	a.id_ente,",
		"	a.nome,",
		"	null::varchar id_struttura,",
		"	null::varchar nome_struttura,",
		"	null::varchar natura_struttura,",
		"	b.pd_percentuale_distribuzione,",
		"	b.pd_algoritmo_distribuzione",
		"from",
		"	ente a,",
		"	covidac_c_perc_dist b",
		"where",
		"	a.id_ente = #{idEnte,jdbcType=INTEGER}",
		"	and a.id_ente = b.id_ente",
		"	and b.data_cancellazione is null",
		"	and b.id_struttura is null",
		"	and now() between b.validita_inizio and coalesce(b.validita_fine, to_timestamp('01/01/2999', 'dd/mm/yyyy'))",
	})
	@Results({
			@Result(column = "id_ente", property = "idEnte", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "nome", property = "nome", jdbcType = JdbcType.VARCHAR),
			@Result(column = "id_struttura", property = "idStruttura", jdbcType = JdbcType.VARCHAR),
			@Result(column = "nome_struttura", property = "nomeStruttura", jdbcType = JdbcType.VARCHAR),
			@Result(column = "natura_struttura", property = "naturaStruttura", jdbcType = JdbcType.VARCHAR),
			@Result(column = "pd_percentuale_distribuzione", property = "pdPercentualeDistribuzione", jdbcType = JdbcType.VARCHAR),
			@Result(column = "pd_algoritmo_distribuzione", property = "pdAlgoritmoDistribuzione", jdbcType = JdbcType.VARCHAR),
			//@Result(column = "tot_posti_target", property = "totPostiTarget", jdbcType = JdbcType.INTEGER)
	})
	EnteExt selectEnte2ByIdEnte(@Param("idEnte") Integer idEnte, @Param("idStruttura") String idStruttura);

	@Select({ 
		"select",
		"	a.id_ente,",
		"	a.nome,",
		"	c.id_struttura,",
		"	c.nome nome_struttura,",
		"	c.nome natura_struttura,",
		"	b.pd_percentuale_distribuzione,",
		"	b.pd_algoritmo_distribuzione",
		"from",
		"	ente a,",
		"	covidac_c_perc_dist b,",
		"	struttura c",
		"where",
		"	a.id_ente = #{idEnte,jdbcType=INTEGER}",
		"	and a.id_ente = b.id_ente",
		"	and c.id_struttura = b.id_struttura",
		"	and b.id_struttura = #{idStruttura,jdbcType=VARCHAR}",
		"	and b.data_cancellazione is null",
		"	and now() between b.validita_inizio and coalesce(b.validita_fine, to_timestamp('01/01/2999', 'dd/mm/yyyy'))"
	})
	@Results({
			@Result(column = "id_ente", property = "idEnte", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "nome", property = "nome", jdbcType = JdbcType.VARCHAR),
			@Result(column = "id_struttura", property = "idStruttura", jdbcType = JdbcType.VARCHAR),
			@Result(column = "nome_struttura", property = "nomeStruttura", jdbcType = JdbcType.VARCHAR),
			@Result(column = "natura_struttura", property = "naturaStruttura", jdbcType = JdbcType.VARCHAR),
			@Result(column = "pd_percentuale_distribuzione", property = "pdPercentualeDistribuzione", jdbcType = JdbcType.VARCHAR),
			@Result(column = "pd_algoritmo_distribuzione", property = "pdAlgoritmoDistribuzione", jdbcType = JdbcType.VARCHAR),
			//@Result(column = "tot_posti_target", property = "totPostiTarget", jdbcType = JdbcType.INTEGER)
	})
	EnteExt selectEnteByIdEnteIdStruttura(@Param("idEnte") Integer idEnte, @Param("idStruttura") String idStruttura);
	
    @Select({
        "select",
        "rich_id, a.id_ente, rich_periodo_da, rich_periodo_a, rich_data, rich_note, data_creazione, ",
        "data_modifica, data_cancellazione, utente_operazione",
        "from covidac_t_richiesta a, ente b",
        "where a.id_ente = b.id_ente",
        "and a.data_cancellazione is null",
        "and a.id_ente = #{idEnte,jdbcType=INTEGER}",
    })
    @Results({
        @Result(column="rich_id", property="richId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="id_ente", property="idEnte", jdbcType=JdbcType.INTEGER),
        @Result(column="rich_periodo_da", property="richPeriodoDa", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="rich_periodo_a", property="richPeriodoA", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="rich_data", property="richData", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="rich_note", property="richNote", jdbcType=JdbcType.VARCHAR),
        @Result(column="data_creazione", property="dataCreazione", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="data_modifica", property="dataModifica", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="data_cancellazione", property="dataCancellazione", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="utente_operazione", property="utenteOperazione", jdbcType=JdbcType.VARCHAR)
    })
    List<CovidacTRichiesta> selectRichiestaByIdEnte(Integer idEnte);
    
	@Select({
		"select",
		"ric_stato_id, ric_stato_cod, ric_stato_desc, validita_inizio, validita_fine, ",
		"data_creazione, data_modifica, data_cancellazione, utente_operazione", 
		"from covidac_d_richiesta_stato",
		"where ric_stato_cod = #{ricStatoCod,jdbcType=VARCHAR}",
		"and data_cancellazione is null",
		"and now() between validita_inizio and coalesce (validita_fine, to_timestamp('01/01/2999', 'dd/mm/yyyy'))"
	})
	@Results({ @Result(column = "ric_stato_id", property = "ricStatoId", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "ric_stato_cod", property = "ricStatoCod", jdbcType = JdbcType.VARCHAR),
			@Result(column = "ric_stato_desc", property = "ricStatoDesc", jdbcType = JdbcType.VARCHAR),
			@Result(column = "validita_inizio", property = "validitaInizio", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "validita_fine", property = "validitaFine", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "data_creazione", property = "dataCreazione", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "data_modifica", property = "dataModifica", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "data_cancellazione", property = "dataCancellazione", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "utente_operazione", property = "utenteOperazione", jdbcType = JdbcType.VARCHAR) })
	CovidacDRichiestaStato selectStatoRichiestaByStatoCod(String ricStatoCod);
	
    @Select({
    	"select",
    	"stato.ric_stato_id,",
    	"ric_stato_cod,",
    	"ric_stato_desc",
    	"from",
    	"covidac_t_richiesta_det det,",
    	"covidac_r_richiesta_det_stato rel,",
    	"covidac_d_richiesta_stato stato",
    	"where",
    	"det.ricdet_id = #{ricdetId,jdbcType=INTEGER}",
    	"and det.ricdet_id = rel.ricdet_id",
    	"and rel.ric_stato_id = stato.ric_stato_id",
    	"and now() between rel.validita_inizio and coalesce (rel.validita_fine, to_timestamp('01/01/2999', 'dd/mm/yyyy'))",
    	"and det.data_cancellazione is null",
    	"and rel.data_cancellazione is null",
    	"and stato.data_cancellazione is null"
    })
    @Results({
        @Result(column="ricdet_id", property="ricdetId", jdbcType=JdbcType.INTEGER),
        @Result(column="ric_stato_id", property="ricStatoId", jdbcType=JdbcType.INTEGER),
        @Result(column="ric_stato_cod", property="ricStatoCod", jdbcType=JdbcType.VARCHAR),
        @Result(column="ric_stato_desc", property="ricStatoDesc", jdbcType=JdbcType.VARCHAR),
    })
    CovidacDRichiestaStato selectByIdDettaglioRichiesta(Integer ricdetId);
    
    @Select({
	    "select covidac_d_ordine_stato.ord_stato_id, ord_stato_cod, ord_stato_desc",
	    "from ",
	    "covidac_t_ordine,",
	    "covidac_r_ordine_stato,",
	    "covidac_d_ordine_stato",
	    "where covidac_t_ordine.ord_id = #{ordId,jdbcType=INTEGER}",
	    "and covidac_t_ordine.ord_id = covidac_r_ordine_stato.ord_id",
	    "and covidac_r_ordine_stato.ord_stato_id = covidac_d_ordine_stato.ord_stato_id",
	    "and now() between covidac_r_ordine_stato.validita_inizio and coalesce (covidac_r_ordine_stato.validita_fine, to_timestamp('01/01/2999', 'dd/mm/yyyy'))"
    })
    @Results({
        @Result(column="ord_stato_id", property="ordStatoId", jdbcType=JdbcType.INTEGER),
        @Result(column="ord_stato_cod", property="ordStatoCod", jdbcType=JdbcType.VARCHAR),
        @Result(column="ord_stato_desc", property="ordStatoDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="ord_id", property="ordId", jdbcType=JdbcType.INTEGER)
    })
    List<CovidacDOrdineStato> selectByIdrdine(Integer ordId);
    
    @Select({
    	"select stato.ric_stato_id, ric_stato_cod, ric_stato_desc",
    	"from",
    	"covidac_t_richiesta_det dett,",
    	"covidac_r_richiesta_det_stato relazione,",
    	"covidac_d_richiesta_stato stato",
    	"where dett.ricdet_id = relazione.ricdet_id",
    	"and relazione.ric_stato_id = stato.ric_stato_id",
	    "and now() between relazione.validita_inizio and coalesce (relazione.validita_fine, to_timestamp('01/01/2999', 'dd/mm/yyyy'))",
    	"and dett.ricdet_id = #{ricdetId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ric_stato_id", property="ricStatoId", jdbcType=JdbcType.INTEGER),
        @Result(column="ric_stato_cod", property="ricStatoCod", jdbcType=JdbcType.VARCHAR),
        @Result(column="ric_stato_desc", property="ricStatoDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="ricdet_id", property="ricdetId", jdbcType=JdbcType.INTEGER)
    })
    List<CovidacDRichiestaStato> selectStatoRichiestaByIdDettaglioRichiesta(Integer ricdetId);
    
	@Select({
		"select", 
		"ordine.ord_id, ordine.ord_identificativo, ordine.ord_data, ordine.ord_data_consegna_prevista, ordine.ord_data_consegna, ",
		"ordine.prod_id, ordine.quantita_ordinata, ordine.quantita_consegnata, ordine.data_creazione, ordine.data_modifica, ",
		"ordine.data_cancellazione, ordine.utente_operazione",
    	"from",
    	"covidac_t_richiesta_det dett,",
    	"covidac_r_ordine_ric_det relazione,",
    	"covidac_t_ordine ordine",
    	"where dett.ricdet_id = relazione.ricdet_id",
    	"and relazione.ord_id = ordine.ord_id",
    	"and dett.ricdet_id = #{ordId,jdbcType=INTEGER}"
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
	@Result(column = "ricdet_id", property = "ricdetId", jdbcType = JdbcType.INTEGER)
    })
    List<CovidacTOrdine> selectOrdineByIdDettaglioRichiesta(Integer ricdetId);

	@Select({ "select", "prod_id, prod_cod, prod_nome, prod_desc, prod_consumabile, validita_inizio, ",
		"validita_fine, data_creazione, data_modifica, data_cancellazione, utente_operazione",
		"from covidac_t_prod", "where prod_id = #{prodId,jdbcType=INTEGER}" })
	@Results({ @Result(column = "prod_id", property = "prodId", jdbcType = JdbcType.INTEGER, id = true),
	@Result(column = "prod_cod", property = "prodCod", jdbcType = JdbcType.VARCHAR),
	@Result(column = "prod_nome", property = "prodNome", jdbcType = JdbcType.VARCHAR),
	@Result(column = "prod_desc", property = "prodDesc", jdbcType = JdbcType.VARCHAR),
	@Result(column = "prod_consumabile", property = "prodConsumabile", jdbcType = JdbcType.BIT),
	@Result(column = "validita_inizio", property = "validitaInizio", jdbcType = JdbcType.TIMESTAMP),
	@Result(column = "validita_fine", property = "validitaFine", jdbcType = JdbcType.TIMESTAMP),
	@Result(column = "data_creazione", property = "dataCreazione", jdbcType = JdbcType.TIMESTAMP),
	@Result(column = "data_modifica", property = "dataModifica", jdbcType = JdbcType.TIMESTAMP),
	@Result(column = "data_cancellazione", property = "dataCancellazione", jdbcType = JdbcType.TIMESTAMP),
	@Result(column = "utente_operazione", property = "utenteOperazione", jdbcType = JdbcType.VARCHAR) })
	List<CovidacTProd> selectByPrimarydKey(Integer prodId);
	
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
    List<CovidacTOrdine> selectAllDettagliRichiesta(Integer ricdetId);
    
	@Select({
		"<script>",
		"select",
		"	ricdett.ricdet_id,",
		"	ricdett.rich_id,",
		"	ricdett.quantita,",
		"	ricdett.fabbisogno_giornaliero,",
		"	ricdett.fabbisogno_settimanale,",
		"	ricdett.dotazione_attuale,",
		"	ricdett.data_creazione,",
		"	ricdett.data_modifica,",
		"	ricdett.data_cancellazione,",
		"	ricdett.utente_operazione,",
		"	ricdett.id_struttura,",
		"	str.nome struttura_nome,",
		"	str.natura struttura_natura,",
		"	str.id_ente struttura_idente,",
		"	ente.id_ente ente_id,",
		"	ente.nome ente_nome,",
		"	dricstato.ric_stato_cod dett_stato_codice,",
		"	dricstato.ric_stato_desc dett_stato_descrizione,",
		"	proddett.prod_cod dett_prod_cod,",
		"	proddett.prod_nome dett_prod_nome,",
		"	proddett.prod_desc dett_prod_desc,",
		"	ordine.ord_id,",
		"	ordine.ord_identificativo,",
		"	ordine.ord_data,",
		"	ordine.ord_data_consegna_prevista,",
		"	ordine.ord_data_consegna,",
		"	ordine.prod_id ord_prod_id,",
		"	ordine.quantita_ordinata ord_quantita_ordinata,",
		"	ordine.quantita_consegnata ord_quantita_consegnata,",
		"	ordine.data_creazione ord_data_creazione,",
		"	prod.prod_cod ord_prod_cod,",
		"	prod.prod_nome ord_prod_nome,",
		"	prod.prod_desc ord_prod_desc,",
		"	dordstato.ord_stato_cod,",
		"	dordstato.ord_stato_desc",
		"from",
		"	covidac_t_richiesta_det ricdett",
		"	inner join covidac_t_richiesta ric on ricdett.rich_id = ric.rich_id",
		"	inner join covidac_t_prod proddett on ricdett.prod_id = proddett.prod_id",
		"	inner join ente on ric.id_ente = ente.id_ente",
		"	inner join covidac_r_richiesta_det_stato rricstato on ricdett.ricdet_id = rricstato.ricdet_id",
		"	and rricstato.data_cancellazione is null",
		"	and now() between rricstato.validita_inizio and coalesce (rricstato.validita_fine, to_timestamp('01/01/2999', 'dd/mm/yyyy'))",
		"	inner join covidac_d_richiesta_stato dricstato on rricstato.ric_stato_id = dricstato.ric_stato_id",
		"	left join struttura str on ricdett.id_struttura = str.id_struttura",
		"	left join covidac_r_app_struttura rappstr on str.id_struttura = rappstr.id_struttura",
		"	left join covid_d_applicativo app on rappstr.app_id = app.app_id",
		"	and app.data_cancellazione is null",
		"	and now() between app.validita_inizio and coalesce (app.validita_fine, to_timestamp('01/01/2999', 'dd/mm/yyyy'))",
		"	and rappstr.data_cancellazione is null",
		"	and now() between rappstr.validita_inizio and coalesce (rappstr.validita_fine, to_timestamp('01/01/2999', 'dd/mm/yyyy'))",
		"	left join covidac_r_ordine_ric_det rordinedett on ricdett.ricdet_id = rordinedett.ricdet_id",
		"	and rordinedett.data_cancellazione is null",
		"	left join covidac_t_ordine ordine on rordinedett.ord_id = ordine.ord_id",
		"	and ordine.data_cancellazione is null",
		"	left join covidac_t_prod prod on ordine.prod_id = prod.prod_id",
		"	left join covidac_r_ordine_stato rordstato on ordine.ord_id = rordstato.ord_id",
		"	and rordstato.data_cancellazione is null",
		"	and now() between rordstato.validita_inizio and coalesce (rordstato.validita_fine, to_timestamp('01/01/2999', 'dd/mm/yyyy'))",
		"	left join covidac_d_ordine_stato dordstato on rordstato.ord_stato_id = dordstato.ord_stato_id",
		"where",
		"	ricdett.data_cancellazione is null",
		"	and ric.data_cancellazione is null",
  	    "<if test='ricStatoCod != null'>and dricstato.ric_stato_cod = #{ricStatoCod,jdbcType=VARCHAR}</if>",
        "<if test='idEnte != null'>and ente.id_ente = #{idEnte,jdbcType=INTEGER}</if>",
        "</script>"
		})
	@Results({
		@Result(column = "ricdet_id", property = "ricdetId", jdbcType = JdbcType.INTEGER, id = true),
		@Result(column = "rich_id", property = "richId", jdbcType = JdbcType.INTEGER),
		@Result(column = "quantita", property = "quantita", jdbcType = JdbcType.INTEGER),
		@Result(column = "fabbisogno_giornaliero", property = "fabbisognoGiornaliero", jdbcType = JdbcType.INTEGER),
		@Result(column = "fabbisogno_settimanale", property = "fabbisognoSettimanale", jdbcType = JdbcType.INTEGER),
		@Result(column = "dotazione_attuale", property = "dotazioneAttuale", jdbcType = JdbcType.INTEGER),
		@Result(column = "data_creazione", property = "dataCreazione", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "data_modifica", property = "dataModifica", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "data_cancellazione", property = "dataCancellazione", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "utente_operazione", property = "utenteOperazione", jdbcType = JdbcType.VARCHAR),
        @Result(column = "id_struttura", property = "idStruttura", jdbcType = JdbcType.VARCHAR),
        @Result(column = "struttura_nome", property = "strutturaNome", jdbcType = JdbcType.VARCHAR),
        @Result(column = "struttura_natura", property = "strutturaNatura", jdbcType = JdbcType.VARCHAR),
        @Result(column = "struttura_idente", property = "strutturaIdEnte", jdbcType = JdbcType.INTEGER),
		@Result(column = "ente_id", property = "enteId", jdbcType = JdbcType.INTEGER),
		@Result(column = "ente_nome", property = "enteNome", jdbcType = JdbcType.VARCHAR),
		@Result(column = "dett_stato_codice", property = "dettStatoCodice", jdbcType = JdbcType.VARCHAR),
		@Result(column = "dett_stato_descrizione", property = "dettStatoDescrizione", jdbcType = JdbcType.VARCHAR),
		@Result(column = "dett_prod_cod", property = "dettProdCod", jdbcType = JdbcType.VARCHAR),
		@Result(column = "dett_prod_nome", property = "dettProdNome", jdbcType = JdbcType.VARCHAR),
		@Result(column = "dett_prod_desc", property = "dettProdDesc", jdbcType = JdbcType.VARCHAR),
		@Result(column = "ord_id", property = "ordId", jdbcType = JdbcType.INTEGER, id = true),
		@Result(column = "ord_identificativo", property = "ordIdentificativo", jdbcType = JdbcType.VARCHAR),
		@Result(column = "ord_data", property = "ordData", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "ord_data_consegna_prevista", property = "ordDataConsegnaPrevista", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "ord_data_consegna", property = "ordDataConsegna", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "ord_prod_id", property = "ordProdId", jdbcType = JdbcType.INTEGER),
		@Result(column = "ord_quantita_ordinata", property = "ordQquantitaOrdinata", jdbcType = JdbcType.INTEGER),
		@Result(column = "ord_quantita_consegnata", property = "ordQuantitaConsegnata", jdbcType = JdbcType.INTEGER),
		@Result(column = "ord_data_creazione", property = "ordDataCreazione", jdbcType = JdbcType.TIMESTAMP),
		@Result(column = "ord_prod_cod", property = "ordProdCod", jdbcType = JdbcType.VARCHAR),
		@Result(column = "ord_prod_nome", property = "ordProdNome", jdbcType = JdbcType.VARCHAR),
		@Result(column = "ord_prod_desc", property = "ordProdDesc", jdbcType = JdbcType.VARCHAR),
		@Result(column = "ord_stato_cod", property = "ordStatoCod", jdbcType = JdbcType.VARCHAR),
		@Result(column = "ord_stato_desc", property = "ordStatoDesc", jdbcType = JdbcType.VARCHAR)
	})
	List<CovidacTRichiestaDetCustom> selectDettagliRichiesteByEnteStato(@Param("idEnte") Integer idEnte, @Param("ricStatoCod") String ricStatoCod);

    @Select({
    	"select",
    	"cto.ord_id, cto.ord_identificativo, cto.ord_data, cto.ord_data_consegna_prevista, cto.ord_data_consegna,",
    	"cto.prod_id, cto.quantita_ordinata, cto.quantita_consegnata, cto.data_creazione, cto.data_modifica,",
    	"cto.data_cancellazione, cto.utente_operazione",
      "from",
      "  covidac_t_richiesta_det ctrd,",
      "  covidac_r_ordine_ric_det crord,",
      "  covidac_t_ordine cto,",
      "  covidac_r_ordine_stato cros,",
      "  covidac_d_ordine_stato cdos",
      "where",
      "  ctrd.ricdet_id = crord.ricdet_id",
      "  and crord.ord_id = cto.ord_id",
      "  and cto.ord_id = cros.ord_id",
      "  and cros.ord_stato_id = cdos.ord_stato_id",
      "  and crord.data_cancellazione is null",
      "  and cto.data_cancellazione is null",
      "  and cros.data_cancellazione is null",
      "  and cdos.data_cancellazione is null",
      "  and now() between cros.validita_inizio and coalesce (cros.validita_fine,",
      "  to_timestamp('01/01/2999', 'dd/mm/yyyy'))",
      "  and ctrd.ricdet_id = #{ricdetId,jdbcType=INTEGER}"
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
    CovidacTOrdine selectOrdineByDettaglioRichiesta(Integer ricdetId);

	@Select({ "select", "prodmag_id, magaz_id, prod_id, quantita_disponibile, quantita_distribuita, validita_inizio, ",
		"validita_fine, data_creazione, data_modifica, data_cancellazione, utente_operazione",
		"from covidac_r_magaz_prod", 
		"where prod_id = #{prodId,jdbcType=INTEGER}",
		"and magaz_id = #{magazId,jdbcType=INTEGER}",
		"and now() between validita_inizio and coalesce(validita_fine, to_timestamp('01/01/2999', 'dd/mm/yyyy'))"
		})
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
		@Result(column = "utente_operazione", property = "utenteOperazione", jdbcType = JdbcType.VARCHAR),
		@Result(column = "utente_operazione", property = "utenteOperazione", jdbcType = JdbcType.VARCHAR),
		@Result(column = "utente_operazione", property = "utenteOperazione", jdbcType = JdbcType.VARCHAR)
	})
	CovidacRMagazProd selectProdmagIdForUpdate(@Param("prodId") Integer prodId, @Param("magazId") Integer magazId);

	@Select({ 
		"select", 
		"prod_id, prod_cod, prod_nome, prod_desc, prod_consumabile, validita_inizio, ",
		"validita_fine, data_creazione, data_modifica, data_cancellazione, utente_operazione",
		"from covidac_t_prod",
		"where data_cancellazione is null",
		"and now() between validita_inizio and coalesce(validita_fine, to_timestamp('01/01/2999', 'dd/mm/yyyy'))",
		})
	@Results({ @Result(column = "prod_id", property = "prodId", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "prod_cod", property = "prodCod", jdbcType = JdbcType.VARCHAR),
			@Result(column = "prod_nome", property = "prodNome", jdbcType = JdbcType.VARCHAR),
			@Result(column = "prod_desc", property = "prodDesc", jdbcType = JdbcType.VARCHAR),
			@Result(column = "prod_consumabile", property = "prodConsumabile", jdbcType = JdbcType.BIT),
			@Result(column = "validita_inizio", property = "validitaInizio", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "validita_fine", property = "validitaFine", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "data_creazione", property = "dataCreazione", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "data_modifica", property = "dataModifica", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "data_cancellazione", property = "dataCancellazione", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "utente_operazione", property = "utenteOperazione", jdbcType = JdbcType.VARCHAR) })
	List<CovidacTProd> selectAllProducts();

	@Select({ "select", "magaz_id, magaz_nome, magaz_indirizzo, magaz_comune_codice, validita_inizio, ",
		"validita_fine, data_creazione, data_modifica, data_cancellazione, utente_operazione",
		"from covidac_t_magazzino" })
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
	List<CovidacTMagazzino> selectAllMagazzini();

	@Insert({
		"insert into covidac_t_richiesta (id_ente, ", 
		"rich_periodo_a, rich_data, ",
		"rich_note, ", 
		"data_cancellazione, ", 
		"utente_operazione)",
		"values (#{idEnte,jdbcType=INTEGER}, ",
		"now(), now(), ",
		"#{richNote,jdbcType=VARCHAR}, ",
		"#{dataCancellazione,jdbcType=TIMESTAMP}, ",
		"#{utenteOperazione,jdbcType=VARCHAR})" })
	@Options(useGeneratedKeys = true, keyProperty = "richId")
	int insertRic(CovidacTRichiesta record);
	
	@Insert({ "insert into covidac_t_richiesta_det (rich_id, prod_id, ", "quantita, fabbisogno_giornaliero, ",
		"fabbisogno_settimanale, dotazione_attuale, ",
		"data_cancellazione, utente_operazione, id_struttura)",
		"values (#{richId,jdbcType=INTEGER}, #{prodId,jdbcType=INTEGER}, ",
		"#{quantita,jdbcType=INTEGER}, #{fabbisognoGiornaliero,jdbcType=INTEGER}, ",
		"#{fabbisognoSettimanale,jdbcType=INTEGER}, #{dotazioneAttuale,jdbcType=INTEGER}, ",
		"#{dataCancellazione,jdbcType=TIMESTAMP}, #{utenteOperazione,jdbcType=VARCHAR}, #{idStruttura,jdbcType=VARCHAR})" })
	@Options(useGeneratedKeys = true, keyProperty = "ricdetId")
	int insertRicDet(CovidacTRichiestaDet record);
	
	@Insert({ "insert into covidac_r_richiesta_det_stato (ricdet_id, ric_stato_id, ",
		"validita_fine, ",
		"data_cancellazione, utente_operazione)",
		"values (#{ricdetId,jdbcType=INTEGER}, #{ricStatoId,jdbcType=INTEGER}, ",
		"#{validitaFine,jdbcType=TIMESTAMP}, ",
		"#{dataCancellazione,jdbcType=TIMESTAMP}, #{utenteOperazione,jdbcType=VARCHAR})" })
	@Options(useGeneratedKeys = true, keyProperty = "ricdetStatoId")
	int insertRicDetStato(CovidacRRichiestaDetStato record);
	
	@Insert({ "insert into covidac_t_magaz_uscita (magaz_id, id_ente, id_struttura,", "prod_id, quantita, quantita_distribuibile,",
		"data_registrazione, data_consegna, ",
		"data_cancellazione, utente_operazione)",
		"values (#{magazId,jdbcType=INTEGER}, #{idEnte,jdbcType=INTEGER}, #{idStruttura,jdbcType=VARCHAR},",
		"#{prodId,jdbcType=INTEGER}, #{quantita,jdbcType=INTEGER}, #{quantitaDistribuibile,jdbcType=INTEGER},",
		"now(), #{dataConsegna,jdbcType=TIMESTAMP}, ",
		"#{dataCancellazione,jdbcType=TIMESTAMP}, #{utenteOperazione,jdbcType=VARCHAR})" })
	@Options(useGeneratedKeys = true, keyProperty = "maguId")
	int insertMagazUscita(CovidacTMagazUscita record);
	
	@Update({ "update covidac_r_magaz_prod", "set magaz_id = #{magazId,jdbcType=INTEGER},",
		"prod_id = #{prodId,jdbcType=INTEGER},", "quantita_disponibile = #{quantitaDisponibile,jdbcType=INTEGER},",
		"quantita_distribuita = #{quantitaDistribuita,jdbcType=INTEGER},",
		"validita_fine = (now() - interval '1 millisecond'),",
		"data_modifica = now(),",
		"data_cancellazione = #{dataCancellazione,jdbcType=TIMESTAMP},",
		"utente_operazione = #{utenteOperazione,jdbcType=VARCHAR}",
		"where prodmag_id = #{prodmagId,jdbcType=INTEGER}" })
	int updateForDistByPrimaryKey(CovidacRMagazProd record);
	
	@Insert({ "insert into covidac_r_magaz_prod (magaz_id, prod_id, ", "quantita_disponibile, quantita_distribuita, ",
		"validita_fine, ",
		"data_cancellazione, utente_operazione)",
		"values (#{magazId,jdbcType=INTEGER}, #{prodId,jdbcType=INTEGER}, ",
		"#{quantitaDisponibile,jdbcType=INTEGER}, #{quantitaDistribuita,jdbcType=INTEGER}, ",
		"#{validitaFine,jdbcType=TIMESTAMP}, ",
		"#{dataCancellazione,jdbcType=TIMESTAMP}, #{utenteOperazione,jdbcType=VARCHAR})" })
	@Options(useGeneratedKeys = true, keyProperty = "prodmagId")
	int insertRMagazProd(CovidacRMagazProd record);
	
    @Select({
    	"select",
    	"	det.ricdet_id,",
    	"	det.rich_id,",
    	"	det.prod_id,",
    	"	det.quantita,",
    	"	det.fabbisogno_giornaliero,",
    	"	det.fabbisogno_settimanale,",
    	"	det.dotazione_attuale,",
    	"	det.data_creazione,",
    	"	det.data_modifica,",
    	"	det.data_cancellazione,",
    	"	det.utente_operazione,",
    	"	det.id_struttura,",
    	"	str.nome struttura_nome,",
    	"	str.natura struttura_natura,",
    	"	str.id_ente struttura_idente",
    	"from",
    	"	covidac_t_richiesta_det det",
    	"left join struttura str on det.id_struttura = str.id_struttura",
    	"left join covidac_r_app_struttura cras on str.id_struttura = cras.id_struttura",
    	"where",
    	"	det.rich_id = #{richId,jdbcType = INTEGER}",
    	"	and det.data_cancellazione is null"
    })
    @Results({
        @Result(column="ricdet_id", property="ricdetId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="rich_id", property="richId", jdbcType=JdbcType.INTEGER),
        @Result(column="prod_id", property="prodId", jdbcType=JdbcType.INTEGER),
        @Result(column="quantita", property="quantita", jdbcType=JdbcType.INTEGER),
        @Result(column="fabbisogno_giornaliero", property="fabbisognoGiornaliero", jdbcType=JdbcType.INTEGER),
        @Result(column="fabbisogno_settimanale", property="fabbisognoSettimanale", jdbcType=JdbcType.INTEGER),
        @Result(column="dotazione_attuale", property="dotazioneAttuale", jdbcType=JdbcType.INTEGER),
        @Result(column="data_creazione", property="dataCreazione", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="data_modifica", property="dataModifica", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="data_cancellazione", property="dataCancellazione", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="utente_operazione", property="utenteOperazione", jdbcType=JdbcType.VARCHAR),
        @Result(column="id_struttura", property = "idStruttura", jdbcType = JdbcType.VARCHAR),
        @Result(column="struttura_nome", property = "strutturaNome", jdbcType = JdbcType.VARCHAR),
        @Result(column="struttura_natura", property = "strutturaNatura", jdbcType = JdbcType.VARCHAR),
        @Result(column="struttura_idente", property = "strutturaIdEnte", jdbcType = JdbcType.INTEGER)
    })
    List<CovidacTRichiestaDetCustom> selectDettagliByIdRichiesta(Integer richId);
}
