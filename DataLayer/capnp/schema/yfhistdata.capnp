@0x89d283cd39b737ea;
using Java = import "../../../../capnp/schema/java.capnp";
$Java.package("datalayer.objects.msg");
$Java.outerClassname("YFHistDataMsg");

struct YFHistData
{
	symbol @0 :Text;
	date @1 :UInt32;
	open @2 :Float64;
	high @3 :Float64;
	low @4 :Float64;
	close @5 :Float64;
	adv @6 :UInt64;
	adjClose @7 :Float64;
}