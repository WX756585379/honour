DIR=$(dirname $0)
cd $DIR/honour
pod install
open $DIR/honour/honour.xcworkspace
exit
