unzip -d target/alt-manager-workspace  ~/.m2/repository/de/latlon/product/xplanbox/xplan-manager-workspace/3.2-SNAPSHOT/xplan-manager-workspace-3.2-SNAPSHOT.zip 
unzip -d target/neu-manager-workspace target/xplan-manager-workspace-3.2-SNAPSHOT.zip
unzip -d target/alt-manager-workspace-default  ~/.m2/repository/de/latlon/product/xplanbox/xplan-manager-workspace/3.2-SNAPSHOT/xplan-manager-workspace-3.2-SNAPSHOT-default.zip 
unzip -d target/neu-manager-workspace-default target/xplan-manager-workspace-3.2-SNAPSHOT-default.zip

unzip -d target/alt-inspireplu-workspace  ~/.m2/repository/de/latlon/product/xplanbox/xplan-inspireplu-workspace/3.2-SNAPSHOT/xplan-inspireplu-workspace-3.2-SNAPSHOT.zip
unzip -d target/neu-inspireplu-workspace target/xplan-inspireplu-workspace-3.2-SNAPSHOT.zip 
unzip -d target/alt-inspireplu-workspace-default  ~/.m2/repository/de/latlon/product/xplanbox/xplan-inspireplu-workspace/3.2-SNAPSHOT/xplan-inspireplu-workspace-3.2-SNAPSHOT-default.zip
unzip -d target/neu-inspireplu-workspace-default target/xplan-inspireplu-workspace-3.2-SNAPSHOT-default.zip 

unzip -d target/alt-wfs-workspace  ~/.m2/repository/de/latlon/product/xplanbox/xplan-wfs-workspace/3.2-SNAPSHOT/xplan-wfs-workspace-3.2-SNAPSHOT.zip
unzip -d target/neu-wfs-workspace target/xplan-wfs-workspace-3.2-SNAPSHOT.zip 
unzip -d target/alt-wfs-workspace-default  ~/.m2/repository/de/latlon/product/xplanbox/xplan-wfs-workspace/3.2-SNAPSHOT/xplan-wfs-workspace-3.2-SNAPSHOT-default.zip
unzip -d target/neu-wfs-workspace-default target/xplan-wfs-workspace-3.2-SNAPSHOT-default.zip 

unzip -d target/alt-synwfs-workspace  ~/.m2/repository/de/latlon/product/xplanbox/xplansyn-wfs-workspace/3.2-SNAPSHOT/xplansyn-wfs-workspace-3.2-SNAPSHOT.zip
unzip -d target/neu-synwfs-workspace target/xplansyn-wfs-workspace-3.2-SNAPSHOT.zip 
unzip -d target/alt-synwfs-workspace-default  ~/.m2/repository/de/latlon/product/xplanbox/xplansyn-wfs-workspace/3.2-SNAPSHOT/xplansyn-wfs-workspace-3.2-SNAPSHOT-default.zip
unzip -d target/neu-synwfs-workspace-default target/xplansyn-wfs-workspace-3.2-SNAPSHOT-default.zip 

unzip -d target/alt-synwms-workspace  ~/.m2/repository/de/latlon/product/xplanbox/xplansyn-wms-workspace/3.2-SNAPSHOT/xplansyn-wms-workspace-3.2-SNAPSHOT.zip
unzip -d target/neu-synwms-workspace target/xplansyn-wms-workspace-3.2-SNAPSHOT.zip 
unzip -d target/alt-synwms-workspace-default  ~/.m2/repository/de/latlon/product/xplanbox/xplansyn-wms-workspace/3.2-SNAPSHOT/xplansyn-wms-workspace-3.2-SNAPSHOT-default.zip
unzip -d target/neu-synwms-workspace-default target/xplansyn-wms-workspace-3.2-SNAPSHOT-default.zip

unzip -d target/alt-validatorwms-workspace  ~/.m2/repository/de/latlon/product/xplanbox/xplan-validator-wms-workspace/3.2-SNAPSHOT/xplan-validator-wms-workspace-3.2-SNAPSHOT.zip
unzip -d target/neu-validatorwms-workspace target/xplan-validator-wms-workspace-3.2-SNAPSHOT.zip 
unzip -d target/alt-validatorwms-workspace-default  ~/.m2/repository/de/latlon/product/xplanbox/xplan-validator-wms-workspace/3.2-SNAPSHOT/xplan-validator-wms-workspace-3.2-SNAPSHOT-default.zip
unzip -d target/neu-validatorwms-workspace-default target/xplan-validator-wms-workspace-3.2-SNAPSHOT-default.zip

echo '###################### DIFF xplan-manager-workspace'
diff -r target/alt-manager-workspace/ target/neu-manager-workspace/
echo '###################### DIFF xplan-manager-workspace DEFAULT'
diff -r target/alt-manager-workspace-default/ target/neu-manager-workspace-default/

echo '###################### DIFF xplan-inspire-workspace'
diff -r target/alt-inspireplu-workspace/ target/neu-inspireplu-workspace/
echo '###################### DIFF xplan-inspire-workspace DEFAULT'
diff -r target/alt-inspireplu-workspace-default/ target/neu-inspireplu-workspace-default/

echo '###################### DIFF xplan-wfs-workspace'
diff -r target/alt-wfs-workspace/ target/neu-wfs-workspace/
echo '###################### DIFF xplan-wfs-workspace DEFAULT'
diff -r target/alt-wfs-workspace-default/ target/neu-wfs-workspace-default/

echo '###################### DIFF xplansyn-wfs-workspace'
diff -r target/alt-synwfs-workspace/ target/neu-synwfs-workspace/
echo '###################### DIFF xplansyn-wfs-workspace DEFAULT'
diff -r target/alt-synwfs-workspace-default/ target/neu-synwfs-workspace-default/

echo '###################### DIFF xplansyn-wms-workspace'
diff -r target/alt-synwms-workspace/ target/neu-synwms-workspace/
echo '###################### DIFF xplansyn-wms-workspace DEFAULT'
diff -r target/alt-synwms-workspace-default/ target/neu-synwms-workspace-default/

echo '###################### DIFF xplan-validator-wms-workspace'
diff -r target/alt-validatorwms-workspace/ target/neu-validatorwms-workspace/
echo '###################### DIFF xplan-validator-wms-workspace DEFAULT'
diff -r target/alt-validatorwms-workspace-default/ target/neu-validatorwms-workspace-default/
