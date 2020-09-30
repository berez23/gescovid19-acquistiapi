package it.csi.gescovid.acquistiapi.business;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import it.csi.gescovid.acquistiapi.business.be.impl.DistribuzioniApiServiceImpl;
import it.csi.gescovid.acquistiapi.business.be.impl.MeApiServiceImpl;
import it.csi.gescovid.acquistiapi.business.be.impl.OrdiniApiServiceImpl;
import it.csi.gescovid.acquistiapi.business.be.impl.ProdottiApiServiceImpl;
import it.csi.gescovid.acquistiapi.business.be.impl.RichiesteApiServiceImpl;
import it.csi.gescovid.acquistiapi.business.be.impl.RichiesteDettagliApiServiceImpl;
import it.csi.gescovid.acquistiapi.business.be.impl.StruttureApiServiceImpl;
import it.csi.gescovid.acquistiapi.util.SpringInjectorInterceptor;
import it.csi.gescovid.acquistiapi.util.SpringSupportedResource;

@ApplicationPath("restfacade/be")
public class AcquistiapiRestApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();

	public AcquistiapiRestApplication() {

		singletons.add(new DistribuzioniApiServiceImpl());
		singletons.add(new MeApiServiceImpl());
		singletons.add(new ProdottiApiServiceImpl());
		singletons.add(new RichiesteApiServiceImpl());
		singletons.add(new RichiesteDettagliApiServiceImpl());
		singletons.add(new OrdiniApiServiceImpl());
		singletons.add(new StruttureApiServiceImpl());
		singletons.add(new SpringInjectorInterceptor());

		for (Object c : singletons) {
			if (c instanceof SpringSupportedResource) {
				SpringApplicationContextHelper.registerRestEasyController(c);
			}
		}
	}

	@Override
	public Set<Class<?>> getClasses() {
		return empty;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

}
