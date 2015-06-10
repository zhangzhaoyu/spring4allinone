/**
 * Created by zhaoyu on 15-5-29.
 */

var anicell = anicell || {}
anicell.security = anicell.security || {}

anicell.security.AniSecurity = function () {
    this.hmacMd5 = function(key, message){
        return hex_hmac_md5(key, message);
    }

    this.generateRequestToken = function(requestMessage, requestTimestamp, secretHash) {
        return this.hmacMd5(secretHash, requestMessage + requestTimestamp);
    }

    this.generateSecretHash = function(idStr, srcToken){
        var hashToken = $.md5(srcToken);
        return this.hmacMd5(hashToken, idStr);
    }

    this.generateUserRequestToken = function(email, password){
        return this.generateRequestToken(
            email,
            (new Date).getTime(),
            this.generateSecretHash(email, password)
        )
    }
}