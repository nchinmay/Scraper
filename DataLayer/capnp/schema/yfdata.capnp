@0xd382f6a4fa10072f;
using Java = import "../../../../capnp/schema/java.capnp";
$Java.package("datalayer.objects.msg");
$Java.outerClassname("YFDataMsg");

struct YFData
{
	symbol @0 :Text;
	name @1 :Text;
	stockExchange @2 :Text;
	yearLow @3 :Float64;
	yearHigh @4 :Float64;
	changeFromYearLow @5 :Float64;
	changeFromYearHigh @6 :Float64;
	percentChangeFromYearLow @7 :Float64;
	percentChangeFromYearHigh @8 :Float64;
	fiftydayMovingAverage @9 :Float64;
	changeFromFiftydayMovingAverage @10 :Float64;
	twoHundreddayMovingAverage @11 :Float64;
	changeFromTwoHundreddayMovingAverage @12 :Float64;
	averageDailyVolume @13 :UInt64;
	pERatio @14 :Float64;
	pEGRatio @15 :Float64;
	priceSales @16 :Float64;
	priceBook @17 :Float64;
	eBITDA @18 :Float64;
	marketCapitalization @19 :Float64;
	earningsShare @20 :Float64;
	ePSEstimateCurrentYear @21 :Float64;
	ePSEstimateNextYear @22 :Float64;
	ePSEstimateNextQuarter @23 :Float64;
	dividendShare @24 :Float64;
	dividendYield @25 :Float64;
	exDividendDate @26 :Text;
	dividendPayDate @27 :Text;
	priceEPSEstimateCurrentYear @28 :Float64;
	priceEPSEstimateNextYear @29 :Float64;
	oneyrTargetPrice @30 :Float64;
}