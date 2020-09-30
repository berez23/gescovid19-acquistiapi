package it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTProd;

public interface CovidacTProdMapper
		extends it.csi.gescovid.acquistiapi.business.be.impl.mapper.generated.BaseCovidacTProdMapper {

    @Select({
        "select",
        "prod_id, prod_cod, prod_nome, prod_desc, prod_consumabile, validita_inizio, ",
        "validita_fine, data_creazione, data_modifica, data_cancellazione, utente_operazione",
        "from covidac_t_prod",
        "where prod_cod = #{prodCod,jdbcType=VARCHAR}",
        "and data_cancellazione IS NULL"
    })
    @Results({
        @Result(column="prod_id", property="prodId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="prod_cod", property="prodCod", jdbcType=JdbcType.VARCHAR),
        @Result(column="prod_nome", property="prodNome", jdbcType=JdbcType.VARCHAR),
        @Result(column="prod_desc", property="prodDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="prod_consumabile", property="prodConsumabile", jdbcType=JdbcType.BIT),
        @Result(column="validita_inizio", property="validitaInizio", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="validita_fine", property="validitaFine", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="data_creazione", property="dataCreazione", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="data_modifica", property="dataModifica", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="data_cancellazione", property="dataCancellazione", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="utente_operazione", property="utenteOperazione", jdbcType=JdbcType.VARCHAR)
    })
    CovidacTProd selectByCodiceProdotto(String prodCod);
}
