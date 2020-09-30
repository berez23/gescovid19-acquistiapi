package it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTRichiesta;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.generated.BaseCovidacTRichiestaMapper;

public interface CovidacTRichiestaMapper extends BaseCovidacTRichiestaMapper {


    @Select({
        "select",
        "rich_id, a.id_ente, rich_periodo_da, rich_periodo_a, rich_data, rich_note, data_creazione, ",
        "data_modifica, data_cancellazione, utente_operazione",
        "from covidac_t_richiesta a, ente b",
        "where a.id_ente = b.id_ente",
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
    List<CovidacTRichiesta> selectByIdEnte(Integer idEnte);
}
