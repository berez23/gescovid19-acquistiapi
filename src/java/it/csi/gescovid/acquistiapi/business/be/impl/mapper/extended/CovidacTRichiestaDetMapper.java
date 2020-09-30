package it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTRichiestaDet;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.generated.BaseCovidacTRichiestaDetMapper;

public interface CovidacTRichiestaDetMapper extends BaseCovidacTRichiestaDetMapper {

    @Select({
        "select",
        "ricdet_id, rich_id, prod_id, quantita, fabbisogno_giornaliero, fabbisogno_settimanale, ",
        "dotazione_attuale, data_creazione, data_modifica, data_cancellazione, utente_operazione, id_struttura",
        "from covidac_t_richiesta_det",
        "where rich_id = #{richId,jdbcType=INTEGER}",
        "and data_cancellazione is null"
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
        @Result(column = "id_struttura", property = "idStruttura", jdbcType = JdbcType.VARCHAR)
    })
    List<CovidacTRichiestaDet> selectByIdRichiesta(Integer richId);
}
