import jenkins.model.*
import hudson.security.*

def env = System.getenv()

def jenkins = Jenkins.getInstance()
if(!(jenkins.getSecurityRealm() instanceof HudsonPrivateSecurityRealm))
    jenkins.setSecurityRealm(new HudsonPrivateSecurityRealm(false))

if(!(jenkins.getAuthorizationStrategy() instanceof GlobalMatrixAuthorizationStrategy))
    jenkins.setAuthorizationStrategy(new GlobalMatrixAuthorizationStrategy())

// create new Jenkins user account
// username & password from environment variables
def user = jenkins.getSecurityRealm().createAccount(env.ADMIN_USER, env.ADMIN_PASS)
user.save()
jenkins.getAuthorizationStrategy().add(Jenkins.ADMINISTER, env.ADMIN_USER)

jenkins.save()
