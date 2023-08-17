# e.g. 1840
ticketIdNo=${1:-}
srcBranchOrTag=${2:-origin/main}
echo "create code drop, ticketId is XPLANBOX-$ticketIdNo, source branch is $srcBranchOrTag"
branchName=xplanbox-$ticketIdNo-$(date +'%m-%d')-code-drop
git fetch remote-opencode
git checkout -b $branchName remote-opencode/main
previousCommit=`git rev-parse HEAD`
git fetch origin
git merge --strategy-option theirs --allow-unrelated-histories $srcBranchOrTag
git reset $previousCommit
git add -A
lastMainCommit=`git rev-parse $srcBranchOrTag`
git commit -m "code drop 6.1-SNAPSHOT: $lastMainCommit"
git push remote-opencode $branchName
