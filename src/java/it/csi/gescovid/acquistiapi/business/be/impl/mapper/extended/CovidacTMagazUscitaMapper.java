package it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTMagazUscita;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.generated.BaseCovidacTMagazUscitaMapper;

public interface CovidacTMagazUscitaMapper extends BaseCovidacTMagazUscitaMapper {

    @Select({
        "select",
        "magu_id, magaz_id, id_ente, prod_id, quantita, data_registrazione, data_consegna, ",
        "data_creazione, data_modifica, data_cancellazione, utente_operazione",
        "from covidac_t_magaz_uscita",
        "where id_ente = #{idEnte,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="magu_id", property="maguId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="magaz_id", property="magazId", jdbcType=JdbcType.INTEGER),
        @Result(column="id_ente", property="idEnte", jdbcType=JdbcType.INTEGER),
        @Result(column="prod_id", property="prodId", jdbcType=JdbcType.INTEGER),
        @Result(column="quantita", property="quantita", jdbcType=JdbcType.INTEGER),
        @Result(column="data_registrazione", property="dataRegistrazione", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="data_consegna", property="dataConsegna", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="data_creazione", property="dataCreazione", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="data_modifica", property="dataModifica", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="data_cancellazione", property="dataCancellazione", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="utente_operazione", property="utenteOperazione", jdbcType=JdbcType.VARCHAR)
    })
    List<CovidacTMagazUscita> selectByIdEnte(Integer idEnte);

}
