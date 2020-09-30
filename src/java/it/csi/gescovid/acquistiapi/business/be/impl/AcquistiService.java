package it.csi.gescovid.acquistiapi.business.be.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacDOrdineStato;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacDRichiestaStato;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTMagazUscita;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTMagazzino;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTOrdine;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.CovidacTProd;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.Ente;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.Profilo;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.custom.CovidacTRichiestaDetCustom;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.dto.extended.EnteExt;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.CovidacTMagazUscitaMapper;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.CovidacTMagazzinoMapper;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.CovidacTOrdineMapper;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.CovidacTProdMapper;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.CovidacTRichiestaDetMapper;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.CovidacTRichiestaMapper;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.EnteMapper;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.RichiesteServiceMapper;
import it.csi.gescovid.acquistiapi.business.be.impl.mapper.extended.UtentiMapper;
import it.csi.gescovid.acquistiapi.dto.ModelDistribuzione;
import it.csi.gescovid.acquistiapi.dto.ModelEnte;
import it.csi.gescovid.acquistiapi.dto.ModelMagazzino;
import it.csi.gescovid.acquistiapi.dto.ModelOrdine;
import it.csi.gescovid.acquistiapi.dto.ModelOrdineStato;
import it.csi.gescovid.acquistiapi.dto.ModelProdotto;
import it.csi.gescovid.acquistiapi.dto.ModelRichiestaDettaglio;
import it.csi.gescovid.acquistiapi.dto.ModelRichiestaDettaglioStandalone;
import it.csi.gescovid.acquistiapi.dto.ModelRichiestaDettaglioStato;
import it.csi.gescovid.acquistiapi.dto.ModelStruttura;
import it.csi.gescovid.acquistiapi.util.CaUtils;

public class AcquistiService extends RESTBaseService {

	@Autowired
	EnteMapper enteMapper;

	@Autowired
	UtentiMapper utentiMapper;

	@Autowired
	RichiesteServiceMapper richiesteServiceMapper;

	@Autowired
	CovidacTRichiestaMapper covidacTRichiestaMapper;

	@Autowired
	CovidacTRichiestaDetMapper covidacTRichiestaDetMapper;

	@Autowired
	CovidacTProdMapper covidacTProdMapper;

	@Autowired
	CovidacTOrdineMapper covidacTOrdineMapper;

	@Autowired
	CovidacTMagazzinoMapper covidacTMagazzinoMapper;

	@Autowired
	CovidacTMagazUscitaMapper covidacTMagazUscitaMapper;

	protected boolean utenteAutorizzato(String codiceFiscale, String profilo) {
		boolean utenteAbilitato = false;

		try {
			List<Profilo> elencoProfiliDB = utentiMapper.selectElencoProfili(codiceFiscale);
			Iterator<Profilo> iterator = elencoProfiliDB.iterator();

			while (iterator.hasNext()) {
				Profilo next = iterator.next();
				if (next.getNomeProfilo() != null && profilo.equalsIgnoreCase(next.getNomeProfilo())) {
					utenteAbilitato = true;
				}
			}
		} catch (Exception e) {
			// FIXME Borrelli
		}

		return utenteAbilitato;
	}

	protected ModelDistribuzione recuperaInfoSingolaDistribuzione(Integer maguId, String idStruttura) {

		ModelDistribuzione distribuzione = new ModelDistribuzione();

		CovidacTMagazUscita next = covidacTMagazUscitaMapper.selectByPrimaryKey(maguId);

		distribuzione.setId(CaUtils.toString(next.getMaguId()));
		distribuzione.setQuantita(next.getQuantita());
		distribuzione.setDataConsegna(next.getDataConsegna());
		distribuzione.setDataCreazione(next.getDataCreazione());
		distribuzione.setDataModifica(next.getDataModifica());
		distribuzione.setDataRegistrazione(next.getDataRegistrazione());

		EnteExt recuperaEnteStruttura = recuperaElencoEnteStruttura(next.getIdEnte(), next.getIdStruttura());
		if (recuperaEnteStruttura != null) {

			// Setto l'ente per la singola distribuzione
			ModelEnte enteDB = new ModelEnte();
			enteDB.setId(CaUtils.toString(recuperaEnteStruttura.getIdEnte()));
			enteDB.setNome(recuperaEnteStruttura.getNome());
			distribuzione.setEnte(enteDB);

			// Setto la struttura per la singola distribuzione
			if (null != recuperaEnteStruttura.getIdStruttura()) {
				ModelStruttura strutturaDB = new ModelStruttura();
				strutturaDB.setEnteId(CaUtils.toString(recuperaEnteStruttura.getIdEnte()));
				strutturaDB.setId(recuperaEnteStruttura.getIdStruttura());
				strutturaDB.setNatura(recuperaEnteStruttura.getNaturaStruttura());
				strutturaDB.setNome(recuperaEnteStruttura.getNomeStruttura());
				distribuzione.setStruttura(strutturaDB);
			}
		}

		distribuzione.setMagazzino(recuperaMagazzino(next.getMagazId()));

		distribuzione.setProdotto(recuperaProdotto(next.getProdId()));

		return distribuzione;
	}

	protected ModelMagazzino recuperaMagazzino(Integer prodId) {

		CovidacTMagazzino magazzinoDB = covidacTMagazzinoMapper.selectByPrimaryKey(prodId);

		ModelMagazzino magazzino = new ModelMagazzino();
		magazzino.setId(CaUtils.toString(magazzinoDB.getMagazId()));
		magazzino.setComune(magazzinoDB.getMagazComuneCodice());
		magazzino.setIndirizzo(magazzinoDB.getMagazIndirizzo());
		magazzino.setNome(magazzinoDB.getMagazNome());

		return magazzino;
	}

	protected ModelEnte recuperaEnte(String ente, String idStruttura) {
		EnteExt enteRes = richiesteServiceMapper.selectEnteByIdEnteIdStruttura(CaUtils.toInteger(ente), idStruttura);
		// Ente enteRes = enteMapper.selectByPrimaryKey(CaUtils.toInteger(ente));

		ModelEnte modelEnte = new ModelEnte();

		if (enteRes != null) {
			modelEnte.setId(ente);
			modelEnte.setNome(enteRes.getNome());
			// modelEnte.setPercentualeDistribuzione(enteRes.getPdPercentualeDistribuzione());
			// //FIXME 10-04-2020
		}

		return modelEnte;
	}

	protected ModelEnte recuperaEnte(String ente) {
		Ente enteRes = richiesteServiceMapper.selectEnteByIdEnte(CaUtils.toInteger(ente));
		// Ente enteRes = enteMapper.selectByPrimaryKey(CaUtils.toInteger(ente));

		ModelEnte modelEnte = new ModelEnte();

		if (enteRes != null) {
			modelEnte.setId(ente);
			modelEnte.setNome(enteRes.getNome());
			// modelEnte.setPercentualeDistribuzione(enteRes.getPdPercentualeDistribuzione());
			// //FIXME 10-04-2020
		}

		return modelEnte;
	}

	protected EnteExt recuperaEnteStruttura(Integer idEnte, String idStruttura) {
		EnteExt enteRes = null;

		if (CaUtils.isEmpty(idStruttura)) {
			enteRes = richiesteServiceMapper.selectEnte2ByIdEnte(idEnte, idStruttura);
		} else {
			enteRes = richiesteServiceMapper.selectEnteByIdEnteIdStruttura(idEnte, idStruttura);
		}

		// Ente enteRes = enteMapper.selectByPrimaryKey(idEnte);

//		ModelEnte modelEnte = new ModelEnte();
//
//		if (enteRes != null) {
//			modelEnte.setId(CaUtils.toString(idEnte));
//			modelEnte.setNome(enteRes.getNome());
//			// modelEnte.setPercentualeDistribuzione(enteRes.getPdPercentualeDistribuzione());
//			// //FIXME 10-04-2020
//		}
//		return modelEnte;

		return enteRes;
	}

	protected EnteExt recuperaElencoEnteStruttura(Integer idEnte, String idStruttura) {
		EnteExt enteRes = richiesteServiceMapper.selectEnteByIdEnteIdStruttura(idEnte, idStruttura);

		return enteRes;
	}

	protected ModelEnte recuperaEnte(Integer idEnte) {
		Ente enteRes = richiesteServiceMapper.selectEnteByIdEnte(idEnte);
		// Ente enteRes = enteMapper.selectByPrimaryKey(idEnte);

		ModelEnte modelEnte = new ModelEnte();

		if (enteRes != null) {
			modelEnte.setId(CaUtils.toString(idEnte));
			modelEnte.setNome(enteRes.getNome());
			// modelEnte.setPercentualeDistribuzione(enteRes.getPdPercentualeDistribuzione());
			// //FIXME 10-04-2020
		}
		return modelEnte;
	}

	protected ModelProdotto recuperaProdotto(Integer prodId) {

		CovidacTProd prodottoDB = covidacTProdMapper.selectByPrimaryKey(prodId);

		ModelProdotto prodotto = new ModelProdotto();

		if (prodottoDB != null) {
			prodotto.setCodice(prodottoDB.getProdCod());
			prodotto.setDescrizione(prodottoDB.getProdDesc());
			prodotto.setNome(prodottoDB.getProdNome());
		}
		return prodotto;
	}

	protected ModelProdotto recuperaProdottoPerCodice(String prodCod) {

		CovidacTProd prodottoDB = covidacTProdMapper.selectByCodiceProdotto(prodCod);

		ModelProdotto prodotto = new ModelProdotto();

		if (prodottoDB != null) {
			prodotto.setCodice(prodottoDB.getProdCod());
			prodotto.setDescrizione(prodottoDB.getProdDesc());
			prodotto.setNome(prodottoDB.getProdNome());
		}
		return prodotto;
	}

	protected Integer getIdMagazzino() {
		List<CovidacTMagazzino> selectAll = covidacTMagazzinoMapper.selectAll();

		return selectAll.get(0).getMagazId();
	}

	protected ModelOrdine recuperaOrdine(Integer ricdetId) {

		CovidacTOrdine ordineDB = richiesteServiceMapper.selectOrdineByDettaglioRichiesta(ricdetId);

		ModelOrdine modelOrdine = new ModelOrdine();

		if (ordineDB != null) {
			modelOrdine.setDataConsegna(ordineDB.getOrdDataConsegna());
			modelOrdine.setDataConsegnaPrevista(ordineDB.getOrdDataConsegnaPrevista());
			modelOrdine.setDataCreazione(ordineDB.getDataCreazione());
			modelOrdine.setDataOrdine(ordineDB.getOrdData());
			modelOrdine.setId(ordineDB.getOrdId());
			modelOrdine.setIdentificativoOrdine(ordineDB.getOrdIdentificativo());
			modelOrdine.setProdotto(recuperaProdotto(ordineDB.getProdId()));
			modelOrdine.setQuantitaConsegnata(ordineDB.getQuantitaConsegnata());
			modelOrdine.setQuantitaOrdinata(ordineDB.getQuantitaOrdinata());
			modelOrdine.setStato(recuperaStatoOrdine(ordineDB.getOrdId()));
		}

		return modelOrdine;
	}

	protected ModelOrdineStato recuperaStatoOrdine(Integer ordId) {
		List<CovidacDOrdineStato> statoOrdineDB = richiesteServiceMapper.selectByIdrdine(ordId);

		ModelOrdineStato modelOrdineStato = new ModelOrdineStato();
		if (statoOrdineDB != null && statoOrdineDB.size() > 0) {
			CovidacDOrdineStato covidacDOrdineStato = statoOrdineDB.get(0);

			modelOrdineStato.setCodice(covidacDOrdineStato.getOrdStatoCod());
			modelOrdineStato.setDescrizione(covidacDOrdineStato.getOrdStatoDesc());
		}

		return modelOrdineStato;
	}

	protected List<ModelRichiestaDettaglioStandalone> recuperaDettagli(String ente, String stato, Boolean ordinati) {
		List<ModelRichiestaDettaglioStandalone> elencoDettagli = recuperaDettagli(ente, stato);

		if (ordinati != null) {
			List<ModelRichiestaDettaglioStandalone> elencoDettagliFiltrato = new ArrayList<ModelRichiestaDettaglioStandalone>();

			boolean ordine = ordinati.booleanValue();

			if (ordine) {
				Iterator<ModelRichiestaDettaglioStandalone> iterator2 = elencoDettagli.iterator();
				while (iterator2.hasNext()) {
					ModelRichiestaDettaglioStandalone next = iterator2.next();

					if (next != null && next.getOrdine() != null && next.getOrdine().getId() != null) {
						ModelRichiestaDettaglioStandalone dettaglio = new ModelRichiestaDettaglioStandalone();
						try {
							BeanUtils.copyProperties(dettaglio, next);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						elencoDettagliFiltrato.add(dettaglio);
					}
				}

			} else {
				Iterator<ModelRichiestaDettaglioStandalone> iterator2 = elencoDettagli.iterator();
				while (iterator2.hasNext()) {
					ModelRichiestaDettaglioStandalone next = iterator2.next();

					if (next == null || (next.getOrdine() != null && next.getOrdine().getId() == null)) {
						ModelRichiestaDettaglioStandalone dettaglio = new ModelRichiestaDettaglioStandalone();
						try {
							BeanUtils.copyProperties(dettaglio, next);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						elencoDettagliFiltrato.add(dettaglio);
					}
				}
			}

			return elencoDettagliFiltrato;
		}

		return elencoDettagli;
	}

	private List<ModelRichiestaDettaglioStandalone> recuperaDettagli(String ente, String stato) {
		List<CovidacTRichiestaDetCustom> elencoDettRichiesta = new ArrayList<CovidacTRichiestaDetCustom>();

		elencoDettRichiesta = richiesteServiceMapper.selectDettagliRichiesteByEnteStato(CaUtils.toInteger(ente), stato);

		Iterator<CovidacTRichiestaDetCustom> iterator = elencoDettRichiesta.iterator();

		List<ModelRichiestaDettaglioStandalone> elencoDettagli = new ArrayList<ModelRichiestaDettaglioStandalone>();

		while (iterator.hasNext()) {
			CovidacTRichiestaDetCustom next = iterator.next();

			ModelRichiestaDettaglioStandalone dettaglioStandalone = new ModelRichiestaDettaglioStandalone();

			dettaglioStandalone.setDataCreazione(next.getDataCreazione());
			dettaglioStandalone.setDataModifica(next.getDataModifica());
			dettaglioStandalone.setDataRichiesta(next.getDataCreazione());
			dettaglioStandalone.setDotazioneAttuale(next.getDotazioneAttuale());
			dettaglioStandalone.setFabbisognoGiornaliero(next.getFabbisognoGiornaliero());
			dettaglioStandalone.setFabbisognoSettimanale(next.getFabbisognoSettimanale());
			dettaglioStandalone.setId(CaUtils.toString(next.getRicdetId()));
			dettaglioStandalone.setRichiesta(CaUtils.toString(next.getRichId()));
			dettaglioStandalone.setQuantita(next.getQuantita());

			// Info ENTE
			ModelEnte modelEnte = new ModelEnte();
			modelEnte.setId(CaUtils.toString(next.getEnteId()));
			modelEnte.setNome(next.getEnteNome());
			dettaglioStandalone.setEnte(modelEnte);

			// Info ORDINE
			ModelOrdine modelOrdine = new ModelOrdine();
			modelOrdine.setId(next.getOrdId());
			modelOrdine.setIdentificativoOrdine(next.getOrdIdentificativo());
			modelOrdine.setDataCreazione(next.getOrdDataCreazione());
			modelOrdine.setDataConsegna(next.getOrdDataConsegna());
			modelOrdine.setDataConsegnaPrevista(next.getOrdDataConsegnaPrevista());
			modelOrdine.setDataOrdine(next.getOrdData());
			modelOrdine.setQuantitaConsegnata(next.getOrdQuantitaConsegnata());
			modelOrdine.setQuantitaOrdinata(next.getOrdQquantitaOrdinata());
			ModelProdotto modelProdottoOrdine = new ModelProdotto();
			modelProdottoOrdine.setCodice(next.getOrdProdCod());
			modelProdottoOrdine.setNome(next.getOrdProdNome());
			modelProdottoOrdine.setDescrizione(next.getOrdProdDesc());
			modelOrdine.setProdotto(modelProdottoOrdine);
			ModelOrdineStato modelOrdineStato = new ModelOrdineStato();
			modelOrdineStato.setCodice(next.getOrdStatoCod());
			modelOrdineStato.setDescrizione(next.getOrdStatoDesc());
			modelOrdine.setStato(modelOrdineStato);
			dettaglioStandalone.setOrdine(modelOrdine);

			// Info PRODOTTO
			ModelProdotto modelProdotto = new ModelProdotto();
			modelProdotto.setCodice(next.getDettProdCod());
			modelProdotto.setNome(next.getDettProdNome());
			modelProdotto.setDescrizione(next.getDettProdDesc());
			dettaglioStandalone.setProdotto(modelProdotto);

			// Info STATO
			ModelRichiestaDettaglioStato modelRichiestaDettaglioStato = new ModelRichiestaDettaglioStato();
			modelRichiestaDettaglioStato.setCodice(next.getDettStatoCodice());
			modelRichiestaDettaglioStato.setDescrizione(next.getDettStatoDescrizione());
			dettaglioStandalone.setStato(modelRichiestaDettaglioStato);

			// Info STRUTTURA
			ModelStruttura modelStruttura = new ModelStruttura();
			modelStruttura.setId(next.getIdStruttura());
			modelStruttura.setNome(next.getStrutturaNome());
			modelStruttura.setNatura(next.getStrutturaNatura());
			modelStruttura.setEnteId(CaUtils.toString(next.getStrutturaIdEnte()));
			dettaglioStandalone.setStruttura(modelStruttura);

			elencoDettagli.add(dettaglioStandalone);
		}
		return elencoDettagli;
	}

	protected List<ModelRichiestaDettaglio> recuperaDettagli(Integer richId) {
		List<CovidacTRichiestaDetCustom> selectByIdRichiesta = richiesteServiceMapper
				.selectDettagliByIdRichiesta(richId);

		Iterator<CovidacTRichiestaDetCustom> iterator = selectByIdRichiesta.iterator();

		ArrayList<ModelRichiestaDettaglio> elencoDettagli = new ArrayList<ModelRichiestaDettaglio>();
		while (iterator.hasNext()) {
			CovidacTRichiestaDetCustom next = iterator.next();

			ModelRichiestaDettaglio dettaglio = new ModelRichiestaDettaglio();

			dettaglio.setDataCreazione(next.getDataCreazione());
			dettaglio.setDataModifica(next.getDataModifica());
			dettaglio.setDotazioneAttuale(next.getDotazioneAttuale());
			dettaglio.setFabbisognoGiornaliero(next.getFabbisognoGiornaliero());
			dettaglio.setFabbisognoSettimanale(next.getFabbisognoSettimanale());
			dettaglio.setId(CaUtils.toString(next.getRicdetId()));
			dettaglio.setOrdine(recuperaOrdine(next.getRicdetId()));
			dettaglio.setProdotto(recuperaProdotto(next.getProdId()));
			dettaglio.setQuantita(next.getQuantita());
			dettaglio.setStato(recuperaStatoDettaglioRichiesta(next.getRicdetId()));

			if (CaUtils.isNotEmpty(next.getIdStruttura())) {
				ModelStruttura modelStruttura = new ModelStruttura();
				modelStruttura.setId(next.getIdStruttura());
				modelStruttura.setNome(next.getStrutturaNome());
				modelStruttura.setNatura(next.getStrutturaNatura());
				modelStruttura.setEnteId(CaUtils.toString(next.getStrutturaIdEnte()));
				dettaglio.setStruttura(modelStruttura);
			}

			elencoDettagli.add(dettaglio);
		}

		return elencoDettagli;
	}

	protected ModelRichiestaDettaglioStato recuperaStatoDettaglioRichiesta(Integer ricdetId) {
		CovidacDRichiestaStato statoDettRichiestaDB = richiesteServiceMapper.selectByIdDettaglioRichiesta(ricdetId);

		ModelRichiestaDettaglioStato modelRichiestaDettaglioStato = new ModelRichiestaDettaglioStato();

		if (statoDettRichiestaDB != null) {

			modelRichiestaDettaglioStato.setCodice(statoDettRichiestaDB.getRicStatoCod());
			modelRichiestaDettaglioStato.setDescrizione(statoDettRichiestaDB.getRicStatoDesc());
		}
		return modelRichiestaDettaglioStato;
	}
}
