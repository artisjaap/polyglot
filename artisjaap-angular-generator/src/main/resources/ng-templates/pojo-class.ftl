@Export
class ${name} {
<#list properties as item>
    private ${item.name} : ${item.type};
</#list>

}
