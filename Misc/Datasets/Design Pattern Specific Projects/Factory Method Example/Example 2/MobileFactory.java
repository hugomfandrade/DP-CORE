public abstract class MobileFactory {

	public abstract SpiceMobile constructMobile(String model);

	/**
	 * Defines the process to product mobile. This implementation should'nt be
	 * be changed by clients
	 * 
	 * @param model
	 * @return SpiceMobile
	 */
	public SpiceMobile produceMobile(String model) {

		SpiceMobile mobile = constructMobile(model);
		mobile.prepare();
		mobile.bundle();
		mobile.label();

		return mobile;
	}
}