# e.g. 1840
ticketIdNo=${1:-}
echo "create code drop, ticketId is XPLANBOX-$ticketIdNo"
branchName=xplanbox-$ticketIdNo-$(date +'%m-%d')-code-drop
git fetch remote-opencode
git checkout -b $branchName remote-opencode/main
previousCommit=`git rev-parse HEAD`
git fetch origin
git merge --strategy-option theirs --allow-unrelated-histories origin/main
git reset $previousCommit
git add -A
lastMainCommit=`git rev-parse origin/main`
git commit -m "code drop 6.1-SNAPSHOT: $lastMainCommit"
git push remote-opencode $branchName
