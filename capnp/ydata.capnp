@0x9c7e743597fda189;
using Java = import "java.capnp";
$Java.package("datafetching");
$Java.outerClassname("YDataCP");

struct YData
{
	symbol @0 :Text;
	name @1 :Text;
	yearLow @2 :Float64;
	yearHigh @3 :Float64;
	fiftydayMovingAverage @4 :Float64;
	changeFromFiftydayMovingAverage @5 :Float64;
	twoHundreddayMovingAverage @6 :Float64;
	changeFromTwoHundreddayMovingAverage @7 :Float64;
	averageDailyVolume @8 :UInt64;
	
	pERatio @9 :Float64;
	pEGRatio @10 :Float64;
	eBITDA @11 :Float64;
	marketCapitalization @12 :Float64;
	
	earningsShare @13 :Float64;
	ePSEstimateCurrentYear @14 :Float64;
	ePSEstimateNextYear @15 :Float64;
	ePSEstimateNextQuarter @16 :Float64;
	dividendShare @17 :Float64;
	dividendYield @18 :Float64;
	exDividendDate @19 :Text;
	priceEPSEstimateCurrentYear @20 :Float64;
	priceEPSEstimateNextYear @21 :Float64;
	oneyrTargetPrice @22 :Float64;
}