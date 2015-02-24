@0xb3e67ccfa1e4dc63;
using Java = import "java.capnp";
$Java.package("datalayer.objects.msg");
$Java.outerClassname("YDataMsg");

struct YData
{
	symbol @0 :Text;
	name @1 :Text;
	stockexchange @2 :Text;
	yearlow @3 :Float64;
	yearhigh @4 :Float64;
	changefromyearlow @5 :Float64;
	changefromyearhigh @6 :Float64;
	percentchangefromyearlow @7 :Float64;
	percentchangefromyearhigh @8 :Float64;
	fiftydaymovingaverage @9 :Float64;
	changefromfiftydaymovingaverage @10 :Float64;
	twohundreddaymovingaverage @11 :Float64;
	changefromtwohundreddaymovingaverage @12 :Float64;
	averagedailyvolume @13 :UInt64;
	peratio @14 :Float64;
	pegratio @15 :Float64;
	pricesales @16 :Float64;
	pricebook @17 :Float64;
	ebitda @18 :Float64;
	marketcapitalization @19 :Float64;
	earningsshare @20 :Float64;
	epsestimatecurrentyear @21 :Float64;
	epsestimatenextyear @22 :Float64;
	epsestimatenextquarter @23 :Float64;
	dividendshare @24 :Float64;
	dividendyield @25 :Float64;
	exdividenddate @26 :Text;
	dividendpaydate @27 :Text;
	priceepsestimatecurrentyear @28 :Float64;
	priceepsestimatenextyear @29 :Float64;
	oneyrtargetprice @30 :Float64;
}