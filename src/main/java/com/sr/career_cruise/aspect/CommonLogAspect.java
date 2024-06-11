package com.sr.career_cruise.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

/**
 * ログ出力共通クラス
 * 
 * @author shimokawa
 */
@Aspect
@Component
public class CommonLogAspect {
  /** ロガー */
  private final Logger log = LoggerFactory.getLogger(CommonLogAspect.class);

  /**
   * 指定メソッドの開始、終了ログ出力メソッド
   * 
   * <p>例外処理時は例外内容を出力
   * 
   * @param jp 処理を挿入する場所
   * @return 指定メソッドの返り値
   */   
  @Around("execution(* com.sr.career_cruise..*(..))")
  public Object writeLog(ProceedingJoinPoint jp) {
    Object targetMethodReturn = null;
    // 開始ログ出力
    log.info("start:" + jp.getSignature().toString());
    try {
      // JoinPointのメソッド実行
      targetMethodReturn = jp.proceed();
    } catch (Throwable t) {
      // エラーログを出力
      log.error(t.toString());
    }

    // 終了ログを出力
    log.info("end:" + jp.getSignature().toString());

    return targetMethodReturn;
  }

}
