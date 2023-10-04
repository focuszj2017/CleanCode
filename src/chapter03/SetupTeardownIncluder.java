package chapter03;

import added.*;

public class SetupTeardownIncluder {
    private PageData pageData;
    private WikiPage testPage;
    private StringBuffer newPageContent;
    //    private PageCrawler pageCrawler;
    private boolean suite;

    public static String render(PageData pageData) throws Exception {
        return render(pageData, false);
    }

    public static String render(PageData pageData, boolean isSuite)
            throws Exception {
        return new SetupTeardownIncluder(pageData).render(isSuite);
    }

    private SetupTeardownIncluder(PageData pageData) {
        this.pageData = pageData;
        this.testPage = pageData.getWikiPage();
//        this.pageCrawler = testPage.getPageCrawler();
        this.newPageContent = new StringBuffer();
    }

    private String render(boolean isSuite) {
        // separate bool from constructor for reusing instance
        this.suite = isSuite;
        if (isTestPage()) includeSetupAndTeardownPages();
        return pageData.getHtml();
    }

    private boolean isTestPage() {
        return pageData.hasAttribute("Test");
    }

    private void includeSetupAndTeardownPages() {
        includeSetupPages();
        includePageContent();
        includeTeardownPages();
        updatePageContent();
    }

    private void includeSetupPages() {
        if (suite)
            includeSuitSetupPage();
        includeSetupPage();
    }

    private void includeSuitSetupPage() {
        include(SuiteResponder.SUITE_SETUP_NAME, "-setup");
    }

    private void includeSetupPage() {
        include("SetUp", "-setup");
    }

    private void includePageContent() {
        newPageContent.append(pageData.getContent());
    }

    private void includeTeardownPages() {
        includeTeardownPage();
        if (suite)
            includeSuitTeardownPage();
    }

    private void includeTeardownPage() {
        include("TearDown", "-teardown");
    }

    private void includeSuitTeardownPage() {
        include(SuiteResponder.SUITE_TEARDOWN_NAME, "-teardown");
    }

    private void updatePageContent() {
        pageData.setContent(newPageContent.toString());
    }

    private void include(String pageName, String arg) {
        WikiPage inheritedPage = findInheritedPage(pageName);
        if (inheritedPage != null) {
            String pagePathName = getPathNameForPage(inheritedPage);
            buildIncludeDirective(pagePathName, arg);
        }
    }

    private WikiPage findInheritedPage(String pageName) {
        return PageCrawlerImpl.getInheritedPage(pageName, testPage);
    }

    private String getPathNameForPage(WikiPage page) {
        PageCrawler pageCrawler = page.getPageCrawler();
        WikiPagePath pagePath = pageCrawler.getFullPath(page);
        return PathParser.render(pagePath);
    }

    private void buildIncludeDirective(String pagePathName, String arg) {
        newPageContent
                .append("\n!include ")
                .append(arg)
                .append(" .")
                .append("\n");
    }
}
