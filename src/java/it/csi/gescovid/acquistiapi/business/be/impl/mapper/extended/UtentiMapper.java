package it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.Profilo;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.extended.UtentiExt;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.generated.BaseUtentiMapper;

public interface UtentiMapper extends BaseUtentiMapper {
    @Select({
    	"SELECT utenti.cf_utente, ente.id_ente, ente.nome",
    	"FROM utenti, r_utente_ente, ente",
    	"where utenti.cf_utente=r_utente_ente.cf_utente",
    	"and r_utente_ente.id_ente=ente.id_ente",
    	"and utenti.cf_utente = #{cfUtente,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="cf_utente", property="cfUtente", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="id_ente", property="id", jdbcType=JdbcType.BIGINT),
        @Result(column="nome", property="nome", jdbcType=JdbcType.BIGINT)
    })
    UtentiExt selectInfoEnte(String cfUtente);

    @Select({
        "SELECT profilo.id_profilo, nome_profilo",
        "FROM profilo, utenti, r_utente_profilo",
        "where utenti.cf_utente = #{cfUtente,jdbcType=VARCHAR}",
        "and r_utente_profilo.id_profilo = profilo.id_profilo",
        "and utenti.cf_utente=r_utente_profilo.cf_utente"
    })
    @Results({
        @Result(column="id_profilo", property="idProfilo", jdbcType=JdbcType.BIGINT),
        @Result(column="nome_profilo", property="nomeProfilo", jdbcType=JdbcType.VARCHAR)
    })
    List<Profilo> selectElencoProfili(String cfUtente);

}
