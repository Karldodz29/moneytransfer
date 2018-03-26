package com.etl.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.etl.util.MYSQLConnection;
import com.etl.util.MYSQLHelper;

public class GlobalExchangeRate {
	public int GlobalExchangeId;
	public int CompanyId;
	public String PaymentMethod;
	public int SourceCountryId;
	public int DestinationCountryId;
	public String SpotPrice;
	public String AutoFees;
	public String SellSpotPrice;
	public String SellingExchangeRate;
	public String GlobalExchangeRate;
	public boolean IsActive;
	public String CreatedDate;
	public String Code;
	public boolean IsDeleted;
	public String DeletedDate;
	public String Result;
	public String Error;

	private void setGlobalExchangeId(int GlobalExchangeId) {
		this.GlobalExchangeId = GlobalExchangeId;
	}

	private int getGlobalExchangeId() {
		return GlobalExchangeId;
	}

	private void setCompanyId(int CompanyId) {
		this.CompanyId = CompanyId;
	}

	private int getCompanyId() {
		return CompanyId;
	}

	private void setPaymentMethod(String PaymentMethod) {
		this.PaymentMethod = PaymentMethod;
	}

	private String getPaymentMethod() {
		return PaymentMethod;
	}

	private void setSourceCountryId(int SourceCountryId) {
		this.SourceCountryId = SourceCountryId;
	}

	private int getSourceCountryId() {
		return SourceCountryId;
	}

	private void setDestinationCountryId(int DestinationCountryId) {
		this.DestinationCountryId = DestinationCountryId;
	}

	private int getDestinationCountryId() {
		return DestinationCountryId;
	}

	private void setSpotPrice(String SpotPrice) {
		this.SpotPrice = SpotPrice;
	}

	private String getSpotPrice() {
		return SpotPrice;
	}

	private void setAutoFees(String AutoFees) {
		this.AutoFees = AutoFees;
	}

	private String getAutoFees() {
		return AutoFees;
	}

	private void setSellSpotPrice(String SellSpotPrice) {
		this.SellSpotPrice = SellSpotPrice;
	}

	private String getSellSpotPrice() {
		return SellSpotPrice;
	}

	private void setSellingExchangeRate(String SellingExchangeRate) {
		this.SellingExchangeRate = SellingExchangeRate;
	}

	private String getSellingExchangeRate() {
		return SellingExchangeRate;
	}

	private void setGlobalExchangeRate(String GlobalExchangeRate) {
		this.GlobalExchangeRate = GlobalExchangeRate;
	}

	private String getGlobalExchangeRate() {
		return GlobalExchangeRate;
	}

	private void setCreatedDate(String CreatedDate) {
		this.CreatedDate = CreatedDate;
	}

	private String getCreatedDate() {
		return CreatedDate;
	}

	
	private void setCode(String Code) {
		this.Code = Code;
	}

	private String getCode() {
		return Code;
	}
	
	
	private void setIsActive(Boolean IsActive) {
		this.IsActive = IsActive;
	}

	private Boolean getIsActive() {
		return IsActive;
	}

	private void setIsDeleted(Boolean IsDeleted) {
		this.IsDeleted = IsDeleted;
	}

	private Boolean getIsDeleted() {
		return IsDeleted;
	}

	private void setDeletedDate(String DeletedDate) {
		this.DeletedDate = DeletedDate;
	}

	private String getDeletedDate() {
		return DeletedDate;
	}

	private void setResult(String Result) {
		this.Result = Result;
	}

	private String getResult() {
		return Result;
	}

	private void setError(String Error) {
		this.Error = Error;
	}

	private String getError() {
		return Error;
	}

	public GlobalExchangeRate addupdateGlobalExchangeRateDetails(GlobalExchangeRate _GlobalExchangeRate) {

		Connection _Connection = MYSQLConnection.GetConnection();
		PreparedStatement _PreparedStatement = null;
		MYSQLHelper _MYSQLHelper = new MYSQLHelper();
		try {
			if (_Connection != null) {

				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				String date = format.format(cal.getTime());
				_GlobalExchangeRate.setCreatedDate(date);

				

					ResultSet _Company = _MYSQLHelper.GetResultSet(
							"SELECT * FROM company where Company_Id='" + _GlobalExchangeRate.CompanyId + "'",
							_Connection);
					if (_Company.next()) {

						ResultSet _SourceCountry = _MYSQLHelper.GetResultSet(
								"SELECT * FROM country where country_id='" + _GlobalExchangeRate.SourceCountryId + "'",
								_Connection);
						if (_SourceCountry.next()) {

							ResultSet _DestinationCountry = _MYSQLHelper
									.GetResultSet("SELECT * FROM country where country_id='"
											+ _GlobalExchangeRate.DestinationCountryId + "'", _Connection);
							if (_DestinationCountry.next()) {

								if (_GlobalExchangeRate.GlobalExchangeId <= 0) {
									
									ResultSet _ResultSetGlobalExchangeCheck = _MYSQLHelper
											.GetResultSet("SELECT * FROM globalexchangerate WHERE SourceCountryId ='"
													+ _GlobalExchangeRate.SourceCountryId + "' and DestinationCountryId='"
													+ _GlobalExchangeRate.DestinationCountryId + "' "
															+ "and CompanyId='"
													+ _GlobalExchangeRate.CompanyId + "' and PaymentMethod='"
													+ _GlobalExchangeRate.PaymentMethod + "' and IsDeleted=0", _Connection);
									if (!_ResultSetGlobalExchangeCheck.next()) {
										int Resultlastid = _GlobalExchangeRate.addDataGlobalExchangeRate(
												_GlobalExchangeRate.CompanyId, _GlobalExchangeRate.PaymentMethod,
												_GlobalExchangeRate.SourceCountryId,
												_GlobalExchangeRate.DestinationCountryId, _GlobalExchangeRate.SpotPrice,
												_GlobalExchangeRate.AutoFees, _GlobalExchangeRate.SellSpotPrice,
												_GlobalExchangeRate.SellingExchangeRate,
												_GlobalExchangeRate.GlobalExchangeRate, _GlobalExchangeRate.IsActive,
												_GlobalExchangeRate.CreatedDate);
										_GlobalExchangeRate.setGlobalExchangeId(Resultlastid);
										_GlobalExchangeRate.setResult("Success");
										
										String _code="FLUTSF"+_GlobalExchangeRate.SpotPrice+Resultlastid;
										String sUpdateStatement = "UPDATE globalexchangerate SET Code = ?"	+ " WHERE GlobalExchangeId = ?";
										_PreparedStatement = _Connection.prepareStatement(sUpdateStatement);
										_PreparedStatement.setString(1,_code);
										_PreparedStatement.setInt(2, _GlobalExchangeRate.GlobalExchangeId);
										_GlobalExchangeRate.setCode(_code);
										_PreparedStatement.executeUpdate();								
										
										clear(_GlobalExchangeRate);
									}
									else{
										
										String _Exitscode = _ResultSetGlobalExchangeCheck.getString("Code");
										_GlobalExchangeRate.setResult("Failed!");
										_GlobalExchangeRate.setError("GlobalExchange rate already exists under Code "+_Exitscode +" please edit Fee Sharing Code "+ _Exitscode +" for new requirements");
										clear(_GlobalExchangeRate);
									}
									
								} else {

									ResultSet _getGlobalExchangerateId = _MYSQLHelper.GetResultSet(
											"SELECT * FROM globalexchangerate where IsDeleted=0 and GlobalExchangeId='"
													+ _GlobalExchangeRate.GlobalExchangeId + "'",
											_Connection);
									if (_getGlobalExchangerateId.next()) {

										_GlobalExchangeRate.updateDataGlobalExchangeRate(
												_GlobalExchangeRate.GlobalExchangeId, _GlobalExchangeRate.CompanyId,
												_GlobalExchangeRate.PaymentMethod,
												_GlobalExchangeRate.SourceCountryId,
												_GlobalExchangeRate.DestinationCountryId, _GlobalExchangeRate.SpotPrice,
												_GlobalExchangeRate.AutoFees, _GlobalExchangeRate.SellSpotPrice,
												_GlobalExchangeRate.SellingExchangeRate,
												_GlobalExchangeRate.GlobalExchangeRate, _GlobalExchangeRate.IsActive,
												_GlobalExchangeRate.CreatedDate);
										_GlobalExchangeRate.setResult("Success");
										clear(_GlobalExchangeRate);
									} else {
										_GlobalExchangeRate.setResult("Failed!");
										_GlobalExchangeRate.setError("Invalid GlobalExchange rate Id!");
										clear(_GlobalExchangeRate);
									}
								}
							} else {
								_GlobalExchangeRate.setResult("Failed!");
								_GlobalExchangeRate.setError("Invalid Destination Country Id!");
								clear(_GlobalExchangeRate);
							}
						} else {
							_GlobalExchangeRate.setResult("Failed!");
							_GlobalExchangeRate.setError("Invalid Source Country Id!");
							clear(_GlobalExchangeRate);
						}
					} else {
						_GlobalExchangeRate.setResult("Failed!");
						_GlobalExchangeRate.setError("Invalid Company Id!");
						clear(_GlobalExchangeRate);
					}
				
			} else {
				_GlobalExchangeRate.setResult("Failed!");
				_GlobalExchangeRate.setError("Error in api backend connectivity !");
				clear(_GlobalExchangeRate);
			}
		} catch (Exception e) {
			clear(_GlobalExchangeRate);
		} finally {
			if (_Connection != null) {
				try {
					_Connection.close();
				} catch (SQLException e) {
					clear(_GlobalExchangeRate);
				}
			}
		}

		return _GlobalExchangeRate;
	}

	public int addDataGlobalExchangeRate(int ComapnyId, String PaymentMethod, int SourceCountryId,
			int DestinationCountryId, String SpotPrice, String AutoFees, String SellSpotPrice,
			String SellingExchangeRate, String GlobalExchangeRate, boolean IsActive, String CreatedDate) {
		int _result = 0;
		Connection _Connection = MYSQLConnection.GetConnection();
		PreparedStatement _PreparedStatement = null;
		MYSQLHelper _MYSQLHelper = new MYSQLHelper();
		try {
			String sInsertStatement = "INSERT INTO globalexchangerate(CompanyId,PaymentMethod,SourceCountryId,DestinationCountryId,SpotPrice,AutoFees,SellSpotPrice,SellingExchangeRate,GlobalExchangeRate,IsActive,CreatedDate)";
			sInsertStatement = sInsertStatement + " VALUES(?, ?, ?,?, ?, ?,?,?,?,?,?)";
			_PreparedStatement = _Connection.prepareStatement(sInsertStatement);

			_PreparedStatement.setInt(1, ComapnyId);
			_PreparedStatement.setString(2, PaymentMethod);
			_PreparedStatement.setInt(3, SourceCountryId);
			_PreparedStatement.setInt(4, DestinationCountryId);
			_PreparedStatement.setString(5, SpotPrice);
			_PreparedStatement.setString(6, AutoFees);
			_PreparedStatement.setString(7, SellSpotPrice);
			_PreparedStatement.setString(8, SellingExchangeRate);
			_PreparedStatement.setString(9, GlobalExchangeRate);
			_PreparedStatement.setBoolean(10, IsActive);
			_PreparedStatement.setString(11, CreatedDate);
			_PreparedStatement.executeUpdate();

			ResultSet _GlobalExchangeId = _MYSQLHelper.GetResultSet(
					"SELECT MAX(GlobalExchangeId) AS GlobalExchangeId FROM globalexchangerate", _Connection);
			if (_GlobalExchangeId.next()) {
				int lastid = _GlobalExchangeId.getInt("GlobalExchangeId");
				_result = lastid;
			}

		} catch (Exception e) {

			System.out.println(e.getMessage());
		} finally {
			if (_Connection != null) {
				try {
					_Connection.close();
				} catch (SQLException e) {
					
				}
			}
		}

		return _result;
	}

	public void updateDataGlobalExchangeRate(int GlobalExchangeId, int ComapnyId, String PaymentMethod,
			int SourceCountryId, int DestinationCountryId, String SpotPrice, String AutoFees, String SellSpotPrice,
			String SellingExchangeRate, String GlobalExchangeRate, boolean IsActive, String CreatedDate) {

		Connection _Connection = MYSQLConnection.GetConnection();
		PreparedStatement _PreparedStatement = null;

		try {

			String sUpdatetStatement = "UPDATE globalexchangerate SET CompanyId = ?,PaymentMethod = ?,SourceCountryId = ?,DestinationCountryId = ?,SpotPrice = ?,AutoFees = ?,SellSpotPrice = ?,SellingExchangeRate=?,GlobalExchangeRate=?,IsActive=?,CreatedDate=?"
					+ " WHERE GlobalExchangeId = ?";

			_PreparedStatement = _Connection.prepareStatement(sUpdatetStatement);
			_PreparedStatement.setInt(1, ComapnyId);
			_PreparedStatement.setString(2, PaymentMethod);
			_PreparedStatement.setInt(3, SourceCountryId);
			_PreparedStatement.setInt(4, DestinationCountryId);
			_PreparedStatement.setString(5, SpotPrice);
			_PreparedStatement.setString(6, AutoFees);
			_PreparedStatement.setString(7, SellSpotPrice);
			_PreparedStatement.setString(8, SellingExchangeRate);
			_PreparedStatement.setString(9, GlobalExchangeRate);
			_PreparedStatement.setBoolean(10, IsActive);
			_PreparedStatement.setString(11, CreatedDate);
			_PreparedStatement.setInt(12, GlobalExchangeId);
			_PreparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (_Connection != null) {
				try {
					_Connection.close();
				} catch (SQLException e) {
					
				}
			}
		}
	}

	public static ArrayList<GlobalExchangeRate> getGlobalExchangeRateDetailsByCompany(int CompanyId) {
		Connection _Connection = MYSQLConnection.GetConnection();

		ArrayList<GlobalExchangeRate> _GlobalExchangeRateDetaillist = new ArrayList<GlobalExchangeRate>();

		MYSQLHelper _MYSQLHelper = new MYSQLHelper();
		try {
			if (_Connection != null) {
				if (CompanyId == 0) {
					ResultSet _ResultSet = _MYSQLHelper
							.GetResultSet("SELECT * FROM globalexchangerate where IsDeleted=0", _Connection);

					while (_ResultSet.next()) {

						GlobalExchangeRate _GlobalExchangeRate = new GlobalExchangeRate();
						_GlobalExchangeRate.setGlobalExchangeId(_ResultSet.getInt("GlobalExchangeId"));
						_GlobalExchangeRate.setPaymentMethod(_ResultSet.getString("PaymentMethod"));
						_GlobalExchangeRate.setCompanyId(_ResultSet.getInt("CompanyId"));
						_GlobalExchangeRate.setSourceCountryId(_ResultSet.getInt("SourceCountryId"));
						_GlobalExchangeRate.setDestinationCountryId(_ResultSet.getInt("DestinationCountryId"));
						_GlobalExchangeRate.setSpotPrice(_ResultSet.getString("SpotPrice"));
						_GlobalExchangeRate.setAutoFees(_ResultSet.getString("AutoFees"));
						_GlobalExchangeRate.setSellSpotPrice(_ResultSet.getString("SellSpotPrice"));
						_GlobalExchangeRate.setSellingExchangeRate(_ResultSet.getString("SellingExchangeRate"));
						_GlobalExchangeRate.setGlobalExchangeRate(_ResultSet.getString("GlobalExchangeRate"));
						_GlobalExchangeRate.setIsDeleted(_ResultSet.getBoolean("IsDeleted"));
						_GlobalExchangeRate.setDeletedDate(_ResultSet.getString("DeletedDate"));
						_GlobalExchangeRate.setCreatedDate(_ResultSet.getString("CreatedDate"));
						_GlobalExchangeRate.setCode(_ResultSet.getString("Code"));
						_GlobalExchangeRate.setIsActive(_ResultSet.getBoolean("IsActive"));
						_GlobalExchangeRate.setResult("Sucess");
						_GlobalExchangeRateDetaillist.add(_GlobalExchangeRate);
					}
					_ResultSet.close();
				} else {
					ResultSet _ResultSet = _MYSQLHelper.GetResultSet(
							"SELECT * FROM globalexchangerate where IsDeleted=0 and CompanyId='" + CompanyId + "'",
							_Connection);

					while (_ResultSet.next()) {
						GlobalExchangeRate _GlobalExchangeRate = new GlobalExchangeRate();
						_GlobalExchangeRate.setGlobalExchangeId(_ResultSet.getInt("GlobalExchangeId"));
						_GlobalExchangeRate.setPaymentMethod(_ResultSet.getString("PaymentMethod"));
						_GlobalExchangeRate.setCompanyId(_ResultSet.getInt("CompanyId"));
						_GlobalExchangeRate.setSourceCountryId(_ResultSet.getInt("SourceCountryId"));
						_GlobalExchangeRate.setDestinationCountryId(_ResultSet.getInt("DestinationCountryId"));
						_GlobalExchangeRate.setSpotPrice(_ResultSet.getString("SpotPrice"));
						_GlobalExchangeRate.setAutoFees(_ResultSet.getString("AutoFees"));
						_GlobalExchangeRate.setSellSpotPrice(_ResultSet.getString("SellSpotPrice"));
						_GlobalExchangeRate.setSellingExchangeRate(_ResultSet.getString("SellingExchangeRate"));
						_GlobalExchangeRate.setGlobalExchangeRate(_ResultSet.getString("GlobalExchangeRate"));
						_GlobalExchangeRate.setIsDeleted(_ResultSet.getBoolean("IsDeleted"));
						_GlobalExchangeRate.setDeletedDate(_ResultSet.getString("DeletedDate"));
						_GlobalExchangeRate.setCreatedDate(_ResultSet.getString("CreatedDate"));
						_GlobalExchangeRate.setCode(_ResultSet.getString("Code"));
						_GlobalExchangeRate.setIsActive(_ResultSet.getBoolean("IsActive"));
						_GlobalExchangeRate.setResult("Sucess");
						_GlobalExchangeRateDetaillist.add(_GlobalExchangeRate);
					}
					_ResultSet.close();

				}
			}
		} catch (Exception e) {

		} finally {
			if (_Connection != null) {
				try {
					_Connection.close();
				} catch (SQLException e) {
					
				}
			}
		}

		return _GlobalExchangeRateDetaillist;
	}

	public GlobalExchangeRate getGlobalExchangeRate(int GlobalExchangeId) {
		Connection _Connection = MYSQLConnection.GetConnection();
		MYSQLHelper _MYSQLHelper = new MYSQLHelper();
		GlobalExchangeRate _GlobalExchangeRate = new GlobalExchangeRate();
		try {
			if (_Connection != null) {

				ResultSet _globalExchangeRate = _MYSQLHelper
						.GetResultSet("SELECT * FROM globalexchangerate where GlobalExchangeId='" + GlobalExchangeId
								+ "' and IsDeleted=0", _Connection);
				if (_globalExchangeRate.next()) {

					_GlobalExchangeRate.setGlobalExchangeId(_globalExchangeRate.getInt("GlobalExchangeId"));
					_GlobalExchangeRate.setPaymentMethod(_globalExchangeRate.getString("PaymentMethod"));
					_GlobalExchangeRate.setCompanyId(_globalExchangeRate.getInt("CompanyId"));
					_GlobalExchangeRate.setSourceCountryId(_globalExchangeRate.getInt("SourceCountryId"));
					_GlobalExchangeRate.setDestinationCountryId(_globalExchangeRate.getInt("DestinationCountryId"));
					_GlobalExchangeRate.setSpotPrice(_globalExchangeRate.getString("SpotPrice"));
					_GlobalExchangeRate.setAutoFees(_globalExchangeRate.getString("AutoFees"));
					_GlobalExchangeRate.setSellSpotPrice(_globalExchangeRate.getString("SellSpotPrice"));
					_GlobalExchangeRate.setSellingExchangeRate(_globalExchangeRate.getString("SellingExchangeRate"));
					_GlobalExchangeRate.setGlobalExchangeRate(_globalExchangeRate.getString("GlobalExchangeRate"));
					_GlobalExchangeRate.setIsDeleted(_globalExchangeRate.getBoolean("IsDeleted"));
					_GlobalExchangeRate.setDeletedDate(_globalExchangeRate.getString("DeletedDate"));
					_GlobalExchangeRate.setCreatedDate(_globalExchangeRate.getString("CreatedDate"));
					_GlobalExchangeRate.setCode(_globalExchangeRate.getString("Code"));
					_GlobalExchangeRate.setIsActive(_globalExchangeRate.getBoolean("IsActive"));
					_GlobalExchangeRate.setResult("Sucess");
				} else {
					_GlobalExchangeRate.setResult("Failed!");
					_GlobalExchangeRate.setError("Invalid Global Exchange rate Id !");
					clear(_GlobalExchangeRate);
				}
			}
		} catch (Exception e) {
			clear(_GlobalExchangeRate);
		} finally {
			if (_Connection != null) {
				try {
					_Connection.close();
				} catch (SQLException e) {
					clear(_GlobalExchangeRate);
				}
			}
		}
		return _GlobalExchangeRate;
	}

	
	
	public GlobalExchangeRate updateDataGlobalExchangeRateEnableDisable(int GlobalExchangeId, boolean IsActive) {
		GlobalExchangeRate _GlobalExchangeRate=new GlobalExchangeRate();
		Connection _Connection = MYSQLConnection.GetConnection();
		MYSQLHelper _MYSQLHelper = new MYSQLHelper();
		PreparedStatement _PreparedStatement = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String date = format.format(cal.getTime());
		_GlobalExchangeRate.setCreatedDate(date);
		try {

			ResultSet _ResultSet = _MYSQLHelper.GetResultSet(
					"SELECT * FROM globalexchangerate where IsDeleted=0 and GlobalExchangeId='"
							+ GlobalExchangeId + "'",
					_Connection);
			if (_ResultSet.next()) {
				String sUpdatetStatement = "UPDATE globalexchangerate SET IsActive=?,CreatedDate=?"
						+ " WHERE GlobalExchangeId = ?";

				_PreparedStatement = _Connection.prepareStatement(sUpdatetStatement);			
				_PreparedStatement.setBoolean(1, IsActive);
				_PreparedStatement.setString(2, _GlobalExchangeRate.CreatedDate);
				_PreparedStatement.setInt(3, GlobalExchangeId);
				_PreparedStatement.executeUpdate();
				_GlobalExchangeRate.setResult("Success");
				_GlobalExchangeRate.setGlobalExchangeId(GlobalExchangeId);
				_GlobalExchangeRate.setIsActive(IsActive);
				clear(_GlobalExchangeRate);
			}
			else{
				_GlobalExchangeRate.setResult("Failed");
				_GlobalExchangeRate.setError("Invalid _Global Exchange Rate Id!");
				clear(_GlobalExchangeRate);
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (_Connection != null) {
				try {
					_Connection.close();
				} catch (SQLException e) {
					
				}
			}
		}
		return _GlobalExchangeRate;
	}

	
	public GlobalExchangeRate updateRealdataFeedGlobalExchangeRate(int DestinationCountryId, String SellSpotPrice) {
		GlobalExchangeRate _GlobalExchangeRate =new GlobalExchangeRate();
		Connection _Connection = MYSQLConnection.GetConnection();
		PreparedStatement _PreparedStatement = null;
		MYSQLHelper _MYSQLHelper = new MYSQLHelper();
		try {
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String date = format.format(cal.getTime());
			_GlobalExchangeRate.setCreatedDate(date);
			ResultSet _ResultSet = _MYSQLHelper.GetResultSet(
					"SELECT * FROM globalexchangerate where DestinationCountryId='" + DestinationCountryId + "'",
						_Connection);
				while (_ResultSet.next()) {
					String dd=_ResultSet.getString("SellingExchangeRate");
					 float _SellSpotPrice = Float.parseFloat(SellSpotPrice);
					float _SellingExchangeRate=_ResultSet.getFloat("SellingExchangeRate");				
					float CalculatedExchangeRate=0;
					float FinalCalculatedExchangeRate=0;
					CalculatedExchangeRate= (_SellSpotPrice *_SellingExchangeRate)/100;
					if(dd.startsWith("-"))
					{
						FinalCalculatedExchangeRate=_SellSpotPrice+(CalculatedExchangeRate);
					}
					else{
						FinalCalculatedExchangeRate=_SellSpotPrice+(CalculatedExchangeRate);
					}
				
					String sUpdatetStatement = "UPDATE globalexchangerate SET SellSpotPrice = ?,GlobalExchangeRate=?,CreatedDate=?"
							+ " WHERE GlobalExchangeId = ?";
					_PreparedStatement = _Connection.prepareStatement(sUpdatetStatement);
					_PreparedStatement.setString(1, SellSpotPrice);					
					_PreparedStatement.setFloat(2, FinalCalculatedExchangeRate);					
					_PreparedStatement.setString(3, _GlobalExchangeRate.CreatedDate);
					_PreparedStatement.setInt(4, _ResultSet.getInt("GlobalExchangeId"));				
					_PreparedStatement.executeUpdate();
					_GlobalExchangeRate.setDestinationCountryId(DestinationCountryId);
					_GlobalExchangeRate.setResult("Success");
				}
				_ResultSet.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (_Connection != null) {
				try {
					_Connection.close();
				} catch (SQLException e) {
					
				}
			}
		}
		return _GlobalExchangeRate;
	}

	
	
	public GlobalExchangeRate deleteGlobalExchangeRate(int GlobalExchangeId) {
		Connection _Connection = MYSQLConnection.GetConnection();
		MYSQLHelper _MYSQLHelper = new MYSQLHelper();
		PreparedStatement _PreparedStatement = null;
		GlobalExchangeRate _GlobalExchangeRate = new GlobalExchangeRate();
		try {
			if (_Connection != null) {
				ResultSet _globalExchangeRate = _MYSQLHelper
						.GetResultSet("SELECT * FROM globalexchangerate where GlobalExchangeId='" + GlobalExchangeId
								+ "' and IsDeleted=0", _Connection);
				if (_globalExchangeRate.next()) {
					
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();
					String date = format.format(cal.getTime());
					_GlobalExchangeRate.setDeletedDate(date);
					_GlobalExchangeRate.setIsDeleted((true));
					String sdeleteStatement = "UPDATE globalexchangerate SET IsDeleted = ? " + ",DeletedDate = ? "
							+ " WHERE GlobalExchangeId = ?";
					
					_PreparedStatement = _Connection.prepareStatement(sdeleteStatement);
					_PreparedStatement.setBoolean(1, _GlobalExchangeRate.IsDeleted);
					_PreparedStatement.setString(2, _GlobalExchangeRate.DeletedDate);
					_PreparedStatement.setInt(3, GlobalExchangeId);
					_PreparedStatement.executeUpdate();
					_GlobalExchangeRate.setResult("Sucess");
					_GlobalExchangeRate.setGlobalExchangeId(GlobalExchangeId);
					clear(_GlobalExchangeRate);
				}
				 else {
						_GlobalExchangeRate.setResult("Failed!");
						_GlobalExchangeRate.setError("Invalid Global Exchange rate Id !");
						clear(_GlobalExchangeRate);
					}
			}
		}
		catch (Exception e) {
			
		}
		finally {
			if (_Connection != null) {
				try {
					_Connection.close();
				} catch (SQLException e) {
					clear(_GlobalExchangeRate);
				}
			}
		}
		return _GlobalExchangeRate;
	}
			
	private GlobalExchangeRate clear(GlobalExchangeRate _GlobalExchangeRate) {

		_GlobalExchangeRate.setCompanyId(0);
		_GlobalExchangeRate.setPaymentMethod("");
		_GlobalExchangeRate.setSourceCountryId(0);
		_GlobalExchangeRate.setDestinationCountryId(0);
		_GlobalExchangeRate.setSpotPrice("");
		_GlobalExchangeRate.setSellSpotPrice("");
		_GlobalExchangeRate.setAutoFees("");
		_GlobalExchangeRate.setSellingExchangeRate("");
		_GlobalExchangeRate.setGlobalExchangeRate("");
		_GlobalExchangeRate.setCode("");
		return _GlobalExchangeRate;

	}
}