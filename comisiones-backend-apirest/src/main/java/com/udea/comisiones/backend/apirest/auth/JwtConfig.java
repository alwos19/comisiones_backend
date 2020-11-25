package com.udea.comisiones.backend.apirest.auth;

public class JwtConfig {

	public static final String LLAVE_SECRETA = "una.clave.secreta.123456";
	
	public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\n"
			+ "MIIEpQIBAAKCAQEAt8SC72BGkkT3gi+XPPFaDaIHAGaeyE1dzw8uiOl7WekkvIKW\n"
			+ "atl3Wx1Wf/i/8yPQHxPGKXUWPbDRs9u2ggQTAHPRth1oEdcKav8GzRVRc/c1YD+Q\n"
			+ "7lmT1ycAPm5AQV7riHS4YNWt77Mr6aYd7ogKfvia4sucbAiF427732HYq6DfOd7x\n"
			+ "iYukSneYYv7FVvFJelixggKHyd1cerfNnhhiVYOGqUiW7V8PjDeSdGeOpLZPiSLZ\n"
			+ "q0y4Y27Ll/yf5rgiG1s6giNBY/LQCOuarVikTMrmRHnGWetmrxTcY4yihbwa+OCI\n"
			+ "fTQq41AxsQ4LquGAljA2zHWszaQQ7OuV22YncQIDAQABAoIBAA2IQQQTmN7IiVli\n"
			+ "28Y3F6SNW+qho+uoFtJakdivKNA0JOpky2LO5MUm4enGjYwABSFkuWDwKFUeojKW\n"
			+ "aVglqKiN3tGEVNeLeE7svLl5amgZuip+Q9PpcMOVMaVnRQALV1jTD4jB828FSKSK\n"
			+ "AQZBqlQzRmSRDdG+xu06jP2kHOp2bEbzOgRwm1gkXyWhp+XwhOWzRUfmc36oL0XY\n"
			+ "kR9xV00/ZDXL5v+3MhcmrDvFbeEiaDwZVpxMg6540mQ9+16ga3HXaIHzxVOyr9qY\n"
			+ "lna460K1UCKq6I4XwUm+4/o0Nhgy3c+NpMEKSrlmPjRUbz5r37NS3E+rTx/qyNaX\n"
			+ "ybiORGECgYEA3HE6SVgP05ARjJaO3WU+nrsUj5G10blAeqYJdq5xWE5EcLHxJPrk\n"
			+ "6GI5FrkYy0dnuPCwq2jzm4K2h3ra+ZGzzj/Cw9UD4LVQTKCB6ktS2N0yltdxnV1B\n"
			+ "eDsj8LfAZABSqg3ogKF+0TbiyKkqfOcsCEpTfDGvLbMCZSQJfZQ2hyUCgYEA1Wje\n"
			+ "UdojBx0USXAYMWGaU9ic/FHrI345DaUpCWHjlAXUmSOTc3PmKjjpnahVyKOhesXF\n"
			+ "G2Tf2cZ9BVtjPDUzUpIieIOFDVJwrIDljFkkxkHWwPIEEFINKQrLYPNmYUwtZHuZ\n"
			+ "6SlP8FcvJ8D8TLrYDKfQcQ4C7UTBPxLLzIKZI10CgYEAn8XBRR96eRBWZ9tHuNeE\n"
			+ "x284u8YUTelC/qIRAd/1btdra3LNIMxn1LY8QmftvIgIeVaZ9eKfjfrzaMZ2p6Qe\n"
			+ "+kHkXCOsJ9XCWYeMIdBuP5gy7nlAc5n/tbDHn9TOsKw7Pd3OwkxKqkBppp5VmSnE\n"
			+ "FE4qrD0mhqzT85Jm/onJ67ECgYEAttlbYxZb3MR1lVaN5AGUkTKPj0FBDvAfQim4\n"
			+ "t2k/7JvyRCPPOqSbGKIayBJrLbXFrcO11nrfsV/3zpc2t2d9ycmb71YAsiFomOT3\n"
			+ "yQC4GYZf0oGKKdGjpP6ysybZGZSDcAzXGodjDrIknRMlGTkd0/oVBkjTCShXywr3\n"
			+ "wwpC26ECgYEArN18PIE8DnciJc+aTIMICpMuMwnvnzxaTaivi9mQShU0nnKO46SX\n"
			+ "JfpJdOvQsHXPa+thvOLT+ghkVhhXkEWzTZz0+UGF/+1Y5j6NoE2lxJXFDQIT5yk8\n"
			+ "nM5HsKAcox4uHqt1px6yJpg/QPVJvhXof7V6/0wUf83QnLpdgr7a1yE=\n"
			+ "-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAt8SC72BGkkT3gi+XPPFa\n"
			+ "DaIHAGaeyE1dzw8uiOl7WekkvIKWatl3Wx1Wf/i/8yPQHxPGKXUWPbDRs9u2ggQT\n"
			+ "AHPRth1oEdcKav8GzRVRc/c1YD+Q7lmT1ycAPm5AQV7riHS4YNWt77Mr6aYd7ogK\n"
			+ "fvia4sucbAiF427732HYq6DfOd7xiYukSneYYv7FVvFJelixggKHyd1cerfNnhhi\n"
			+ "VYOGqUiW7V8PjDeSdGeOpLZPiSLZq0y4Y27Ll/yf5rgiG1s6giNBY/LQCOuarVik\n"
			+ "TMrmRHnGWetmrxTcY4yihbwa+OCIfTQq41AxsQ4LquGAljA2zHWszaQQ7OuV22Yn\n"
			+ "cQIDAQAB\n"
			+ "-----END PUBLIC KEY-----";
}
