package com.soyoung.security.utils;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soyoung.common.base.DataBaseEnum;
import com.soyoung.common.utils.MyExceptionUtil;
import com.soyoung.security.base.SecurityUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodec;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtHandler;
import io.jsonwebtoken.JwtHandlerAdapter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.DefaultHeader;
import io.jsonwebtoken.impl.DefaultJws;
import io.jsonwebtoken.impl.DefaultJwsHeader;
import io.jsonwebtoken.impl.DefaultJwt;
import io.jsonwebtoken.impl.TextCodec;
import io.jsonwebtoken.impl.compression.DefaultCompressionCodecResolver;
import io.jsonwebtoken.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt的工具类
 */
@Configuration
public class JwtTokenUtil {

    @Autowired
    private static JwtTokenUtil jwtTokenUtil;

    @Autowired
    private DataBaseEnum dataBaseEnum;

    @PostConstruct
    public synchronized void init() {
        jwtTokenUtil = this;
        jwtTokenUtil.dataBaseEnum = this.dataBaseEnum;
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private static String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + jwtTokenUtil.dataBaseEnum.expiration);
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, jwtTokenUtil.dataBaseEnum.secret)
                .compact();
    }


    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(jwtTokenUtil.dataBaseEnum.secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }


    /**
     * 生成令牌
     *
     * @return 令牌
     */
    public static String generateToken(String userName, String ip) throws Exception {
        Map<String, Object> claims = new HashMap<>(3);
        claims.put("sub", userName);
        claims.put("created", new Date());
        // ip加密保存
        ip = MD5Util.getMD5String(ip);
        claims.put("ip", ip);
        return generateToken(claims);
    }


    /**
     * 从令牌中获取openid
     *
     * @param token 令牌
     * @return 用户名
     */
    public static String getUsernameFromToken(String token) {
        String userid = "";
        try {
            Claims claims = getClaimsFromToken(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            userid = null;
        }
        return userid;
    }


    /**
     * 从令牌中获取ip
     *
     * @param token 令牌
     * @return ip
     */
    public static String getIpFromToken(String token) {
        String ip = "";
        try {
            Claims claims = getClaimsFromToken(token);
            ip = claims.get("ip").toString();
        } catch (Exception e) {
            ip = null;
        }
        return ip;
    }


    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public static Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            if (claims == null) {
                return false;
            }
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            throw MyExceptionUtil.mxe(e);
        }
    }


    /**
     * 验证令牌
     *
     * @param token       令牌
     * @param userDetails 用户
     * @param ip          当前访问ip
     * @return 是否有效
     */
    public static Boolean validateToken(String token, UserDetails userDetails, String ip) {
        SecurityUserDetails user = (SecurityUserDetails) userDetails;
        String openid = getUsernameFromToken(token);
        String tokenip = getIpFromToken(token);
        try {
            // 将ip加密与token中的ip进行比较
            ip = MD5Util.getMD5String(ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // && ip.equals(tokenip)  由于开发时，token只能在线上环境获取，线上的token拿到本地不能访问，所以先将ip校验注释调
        return user != null && (openid.equals(user.getUsername()) && !isTokenExpired(token));
    }


    /**
     * 从SecurityContextHolder中获取openid
     *
     * @return 用户姓名
     */
    public static String getOpenid() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        //将对象转为map
        JSONObject jsonObject = JSONUtil.parseObj(principal);
        // 从map中获取用户姓名
        String info = (String) jsonObject.get("username");
        return info.split("&")[0];
    }

    /**
     * 获取过期token内数据
     *
     * @param token
     * @return
     * @throws IOException
     */
    public static String getRushToken(String token) throws IOException {
        Claims body = test(token).getBody();
        return body.getSubject();
    }


    public static Jws<Claims> test(String jwt) throws IOException {
        String base64UrlEncodedHeader = null;
        String base64UrlEncodedPayload = null;
        String base64UrlEncodedDigest = null;
        ObjectMapper objectMapper = new ObjectMapper();
        int delimiterCount = 0;
        StringBuilder sb = new StringBuilder(128);
        char[] arr$ = jwt.toCharArray();
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            char c = arr$[i$];
            if (c == '.') {
                CharSequence tokenSeq = Strings.clean(sb);
                String token = tokenSeq != null ? tokenSeq.toString() : null;
                if (delimiterCount == 0) {
                    base64UrlEncodedHeader = token;
                } else if (delimiterCount == 1) {
                    base64UrlEncodedPayload = token;
                }

                ++delimiterCount;
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }

        if (delimiterCount != 2) {
            String msg = "JWT strings must contain exactly 2 period characters. Found: " + delimiterCount;
            throw new MalformedJwtException(msg);
        } else {
            if (sb.length() > 0) {
                base64UrlEncodedDigest = sb.toString();
            }

            if (base64UrlEncodedPayload == null) {
                throw new MalformedJwtException("JWT string '" + jwt + "' is missing a body/payload.");
            } else {
                Header header = null;
                CompressionCodec compressionCodec = null;
                String payload;
                if (base64UrlEncodedHeader != null) {
                    payload = TextCodec.BASE64URL.decodeToString(base64UrlEncodedHeader);
                    Map<String, Object> m = objectMapper.readValue(payload, Map.class);
                    ;
                    if (base64UrlEncodedDigest != null) {
                        header = new DefaultJwsHeader(m);
                    } else {
                        header = new DefaultHeader(m);
                    }
                    DefaultCompressionCodecResolver sd = new DefaultCompressionCodecResolver();
                    compressionCodec = sd.resolveCompressionCodec((Header) header);
                }

                if (compressionCodec != null) {
                    byte[] decompressed = compressionCodec.decompress(TextCodec.BASE64URL.decode(base64UrlEncodedPayload));
                    payload = new String(decompressed, Strings.UTF_8);
                } else {
                    payload = TextCodec.BASE64URL.decodeToString(base64UrlEncodedPayload);
                }

                Claims claims = null;
                if (payload.charAt(0) == '{' && payload.charAt(payload.length() - 1) == '}') {
                    Map<String, Object> claimsMap = objectMapper.readValue(payload, Map.class);
                    claims = new DefaultClaims(claimsMap);
                }

                if (base64UrlEncodedDigest != null) {
                    JwsHeader jwsHeader = (JwsHeader) header;
                    SignatureAlgorithm algorithm = null;
                    String object;
                    if (header != null) {
                        object = jwsHeader.getAlgorithm();
                        if (Strings.hasText(object)) {
                            algorithm = SignatureAlgorithm.forName(object);
                        }
                    }

                    if (algorithm == null || algorithm == SignatureAlgorithm.NONE) {
                        object = "JWT string has a digest/signature, but the header does not reference a valid signature algorithm.";
                        throw new MalformedJwtException(object);
                    }

                }
                Jwt zn = null;
                Object body = claims != null ? claims : payload;
                if (base64UrlEncodedDigest != null) {
                    zn = new DefaultJws((JwsHeader) header, body, base64UrlEncodedDigest);
                } else {
                    zn = new DefaultJwt((Header) header, body);
                }
                Jws<Claims> parse = parse(zn, new JwtHandlerAdapter<Jws<Claims>>() {
                    @Override
                    public Jws<Claims> onClaimsJws(Jws<Claims> jws) {
                        return jws;
                    }
                });
                return parse;
            }
        }
    }

    public static <T> T parse(Jwt jwt, JwtHandler<T> handler) {
        if (jwt instanceof Jws) {
            Jws jws = (Jws) jwt;
            Object body = jws.getBody();
            if (body instanceof Claims) {
                return handler.onClaimsJws((Jws<Claims>) jws);
            } else {
                return handler.onPlaintextJws((Jws<String>) jws);
            }
        } else {
            Object body = jwt.getBody();
            if (body instanceof Claims) {
                return handler.onClaimsJwt((Jwt<Header, Claims>) jwt);
            } else {
                return handler.onPlaintextJwt((Jwt<Header, String>) jwt);
            }
        }
    }
}
