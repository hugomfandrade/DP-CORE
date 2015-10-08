public class Client {

	public static void main(String[] args) {

		MobileFactory factory = new LondonMobileFactory();
		SpiceMobile mobile = factory.produceMobile("SpiceHD");

		MobileFactory nyFactory = new NYMobileFactory();
		SpiceMobile mobile2 = factory.produceMobile("SpiceMono");

	}
}