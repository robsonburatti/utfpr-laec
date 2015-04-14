<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
  <html>
  <body>
  <h2>Meus Dados Pessoais</h2>
    <table border="1">
      <tr bgcolor="#9acd32">
        <th>Nome</th>
        <th>Cidade</th>
        <th>Telefone</th>
        <th>E-mail</th>
      </tr>
      <xsl:for-each select="lista/dados_pessoais">
      <tr>
        <td><xsl:value-of select="nome"/></td>
        <td><xsl:value-of select="endereco/cidade"/></td>
        <td><xsl:value-of select="telefone/celular"/></td>
        <td><xsl:value-of select="contato/email"/></td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>