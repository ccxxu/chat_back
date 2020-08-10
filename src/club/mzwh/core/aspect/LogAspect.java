package club.mzwh.core.aspect;

import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LogAspect {
	
	/*
	@Autowired
	private ActionLogService actionService;
	
	@Autowired
	private LogCategoryService logCategoryService;
	
	@Autowired
	private ArticleColumnService articleColumnService;
	
	@Autowired
	private SearchLogService searchLogService;


	@Before("execution(* com.ce..action..*.*(..))")
	public void doBefore(JoinPoint call) throws Exception {
		String className = call.getTarget().getClass().getName();
		String actionMethod = call.getSignature().getName();
		
		if(!"imageRandom".equals(actionMethod)){
			LogCategoryModel model = logCategoryService.getModelByClassAndMethod(className,actionMethod);
			if(model != null){
				Date date = new Date();
				ActionLogModel logModel = new ActionLogModel();
				logModel.setUsername(UserUtil.getName());
				logModel.setUserID(UserUtil.getLoginName());
				logModel.setWebsiteNameString(UserUtil.getCurrentSite());
				logModel.setUserIP(UserUtil.getAddressIP());
				logModel.setActionClass(className);
				logModel.setActionMethod(actionMethod);
				logModel.setLogCategoryModel(model);
				logModel.setCreateTime(date);
				actionService.save(logModel);
			}
		}
	}

	@After("execution(* com.ce..dao..*.*(..))")
	public void doAfter(JoinPoint call) {
		
	}
	
	
	
	/**
	 * @param 切面文章处理
	 */
	/*
	@After("execution(* com.ce.article.action..*.*(..))")
	public void doSearchLogArticle(JoinPoint call) {
		String actionMethod = call.getSignature().getName();
		SearchLogModel searchLogModel = new SearchLogModel();
		try {
			if("auditArticlePost".equals(actionMethod) || "recycleBack".equals(actionMethod) ){
				Object[] obj = call.getArgs();
				ArticleColumnModel model = articleColumnService.get((String)obj[0]);
				if("9".equals(model.getIssueStatus())){
					searchLogModel.setArticleID(model.getCuid());
					searchLogModel.setStatus("N");
					searchLogModel.setSynoTime(new Date());
					searchLogService.save(searchLogModel);
				}
				
			}else if("batchAuditArticlePost".equals(actionMethod)){
				Object[] obj = call.getArgs();
				String[] ids = (String[])obj[0];
				for(String id:ids){
					ArticleColumnModel model = articleColumnService.get(id);
					if("9".equals(model.getIssueStatus())){
						searchLogModel.setArticleID(model.getCuid());
						searchLogModel.setStatus("N");
						searchLogModel.setSynoTime(new Date());
						searchLogService.save(searchLogModel);
					}
				}

			}else if("canelIssueArticlePost".equals(actionMethod) ){
				Object[] obj = call.getArgs();
				searchLogModel.setArticleID((String)obj[0]);
				searchLogModel.setStatus("D");
				searchLogModel.setSynoTime(new Date());
				searchLogService.save(searchLogModel);
			}else if("updatePost".equals(actionMethod) || "deletePost".equals(actionMethod)){
				Object[] obj = call.getArgs();
				if("1".equals(obj[1].toString())){
					searchLogModel.setArticleID((String)obj[0]);
					searchLogModel.setStatus("D");
					searchLogModel.setSynoTime(new Date());
					searchLogService.save(searchLogModel);
				}
			}
		} catch (Exception e) {
			Logger logger = Logger.getLogger(call.getTarget().getClass());
			logger.error(e);
		}
	}
	
	

	@Around("execution(* com.ce..action..*.*(..))")
	public Object doAround(ProceedingJoinPoint pjp) {

		//Logger logger = Logger.getLogger(pjp.getTarget().getClass());
		Object retVal = null;
		try {
			StopWatch clock = new StopWatch();
			clock.start(); // 计时开始
			retVal = pjp.proceed();
			clock.stop();  //计时结束
//			logger.info("Takes:" + clock.getTime() + " ms");
//			logger.info(pjp.getTarget().getClass());
//			logger.info(pjp.getSignature().getName());
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return retVal;

	}

	@AfterThrowing(value="execution(* com.ce..action..*.*(..))",argNames="exception", throwing="exception")
	public void doThrowing(JoinPoint call,Exception exception) {
		Logger logger = Logger.getLogger(call.getTarget().getClass());
		logger.error(exception);
	}
	*/
	
}
