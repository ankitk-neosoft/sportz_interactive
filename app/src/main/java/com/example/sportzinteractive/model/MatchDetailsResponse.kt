package com.example.sportzinteractive.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class MatchDetailsResponse(

	@field:SerializedName("Matchdetail")
	val matchdetail: Matchdetail? = null,

	@field:SerializedName("Innings")
	val innings: List<InningsItem?>? = null,

	@field:SerializedName("Nuggets")
	val nuggets: List<String?>? = null,

	@field:SerializedName("Teams")
	val teams: HashMap<String,TeamDetails>? = HashMap(),

	@field:SerializedName("Notes")
	val notes: Notes? = null
) : Parcelable



@Parcelize
data class InningsItem(

	@field:SerializedName("Bowlers")
	val bowlers: List<BowlersItem?>? = null,

	@field:SerializedName("Batsmen")
	val batsmen: List<BatsmenItem?>? = null,

	@field:SerializedName("Runrate")
	val runrate: String? = null,

	@field:SerializedName("Partnership_Current")
	val partnershipCurrent: PartnershipCurrent? = null,

	@field:SerializedName("PowerPlay")
	val powerPlay: PowerPlay? = null,

	@field:SerializedName("AllottedOvers")
	val allottedOvers: String? = null,

	@field:SerializedName("Penalty")
	val penalty: String? = null,

	@field:SerializedName("Overs")
	val overs: String? = null,

	@field:SerializedName("Noballs")
	val noballs: String? = null,

	@field:SerializedName("Target")
	val target: String? = null,

	@field:SerializedName("Number")
	val number: String? = null,

	@field:SerializedName("FallofWickets")
	val fallofWickets: List<FallofWicketsItem?>? = null,

	@field:SerializedName("Total")
	val total: String? = null,

	@field:SerializedName("Battingteam")
	val battingteam: String? = null,

	@field:SerializedName("Wickets")
	val wickets: String? = null,

	@field:SerializedName("Byes")
	val byes: String? = null,

	@field:SerializedName("Wides")
	val wides: String? = null,

	@field:SerializedName("Legbyes")
	val legbyes: String? = null
) : Parcelable



@Parcelize
data class BowlersItem(

	@field:SerializedName("Noballs")
	val noballs: String? = null,

	@field:SerializedName("Economyrate")
	val economyrate: String? = null,

	@field:SerializedName("Maidens")
	val maidens: String? = null,

	@field:SerializedName("Wickets")
	val wickets: String? = null,

	@field:SerializedName("Dots")
	val dots: String? = null,

	@field:SerializedName("Wides")
	val wides: String? = null,

	@field:SerializedName("Bowler")
	val bowler: String? = null,

	@field:SerializedName("Overs")
	val overs: String? = null,

	@field:SerializedName("Runs")
	val runs: String? = null,

	@field:SerializedName("Isbowlingtandem")
	val isbowlingtandem: Boolean? = null,

	@field:SerializedName("ThisOver")
	val thisOver: List<ThisOverItem?>? = null,

	@field:SerializedName("Isbowlingnow")
	val isbowlingnow: Boolean? = null
) : Parcelable



@Parcelize
data class Series(

	@field:SerializedName("Status")
	val status: String? = null,

	@field:SerializedName("Tour_Name")
	val tourName: String? = null,

	@field:SerializedName("Id")
	val id: String? = null,

	@field:SerializedName("Name")
	val name: String? = null,

	@field:SerializedName("Tour")
	val tour: String? = null
) : Parcelable

@Parcelize
data class Batting(

	@field:SerializedName("Strikerate")
	val strikerate: String? = null,

	@field:SerializedName("Average")
	val average: String? = null,

	@field:SerializedName("Style")
	val style: String? = null,

	@field:SerializedName("Runs")
	val runs: String? = null
) : Parcelable

@Parcelize
data class Venue(

	@field:SerializedName("Id")
	val id: String? = null,

	@field:SerializedName("Name")
	val name: String? = null
) : Parcelable



@Parcelize
data class Bowling(

	@field:SerializedName("Economyrate")
	val economyrate: String? = null,

	@field:SerializedName("Average")
	val average: String? = null,

	@field:SerializedName("Style")
	val style: String? = null,

	@field:SerializedName("Wickets")
	val wickets: String? = null
) : Parcelable

@Parcelize
data class Notes(

	@field:SerializedName("1")
	val jsonMember1: List<String?>? = null,

	@field:SerializedName("2")
	val jsonMember2: List<String?>? = null
) : Parcelable



@Parcelize
data class BatsmenItem(

	@field:SerializedName("Fours")
	val fours: String? = null,

	@field:SerializedName("Sixes")
	val sixes: String? = null,

	@field:SerializedName("Strikerate")
	val strikerate: String? = null,

	@field:SerializedName("Batsman")
	val batsman: String? = null,

	@field:SerializedName("Fielder")
	val fielder: String? = null,

	@field:SerializedName("Dismissal")
	val dismissal: String? = null,

	@field:SerializedName("Dots")
	val dots: String? = null,

	@field:SerializedName("Balls")
	val balls: String? = null,

	@field:SerializedName("Bowler")
	val bowler: String? = null,

	@field:SerializedName("Howout")
	val howout: String? = null,

	@field:SerializedName("Runs")
	val runs: String? = null,

	@field:SerializedName("Isonstrike")
	val isonstrike: Boolean? = null
) : Parcelable

@Parcelize
data class ThisOverItem(

	@field:SerializedName("B")
	val b: String? = null,

	@field:SerializedName("T")
	val t: String? = null
) : Parcelable



@Parcelize
data class Match(

	@field:SerializedName("League")
	val league: String? = null,

	@field:SerializedName("Type")
	val type: String? = null,

	@field:SerializedName("Number")
	val number: String? = null,

	@field:SerializedName("Livecoverage")
	val livecoverage: String? = null,

	@field:SerializedName("Time")
	val time: String? = null,

	@field:SerializedName("Daynight")
	val daynight: String? = null,

	@field:SerializedName("Id")
	val id: String? = null,

	@field:SerializedName("Code")
	val code: String? = null,

	@field:SerializedName("Date")
	val date: String? = null,

	@field:SerializedName("Offset")
	val offset: String? = null
) : Parcelable

@Parcelize
data class Matchdetail(

	@field:SerializedName("Status")
	val status: String? = null,

	@field:SerializedName("Venue")
	val venue: Venue? = null,

	@field:SerializedName("Team_Home")
	val teamHome: String? = null,

	@field:SerializedName("Status_Id")
	val statusId: String? = null,

	@field:SerializedName("Player_Match")
	val playerMatch: String? = null,

	@field:SerializedName("Equation")
	val equation: String? = null,

	@field:SerializedName("Officials")
	val officials: Officials? = null,

	@field:SerializedName("Winningteam")
	val winningteam: String? = null,

	@field:SerializedName("Match")
	val match: Match? = null,

	@field:SerializedName("Result")
	val result: String? = null,

	@field:SerializedName("Weather")
	val weather: String? = null,

	@field:SerializedName("Team_Away")
	val teamAway: String? = null,

	@field:SerializedName("Series")
	val series: Series? = null,

	@field:SerializedName("Tosswonby")
	val tosswonby: String? = null,

	@field:SerializedName("Winmargin")
	val winmargin: String? = null
) : Parcelable



@Parcelize
data class FallofWicketsItem(

	@field:SerializedName("Score")
	val score: String? = null,

	@field:SerializedName("Batsman")
	val batsman: String? = null,

	@field:SerializedName("Overs")
	val overs: String? = null
) : Parcelable



@Parcelize
data class PartnershipCurrent(

	@field:SerializedName("Batsmen")
	val batsmen: List<BatsmenItem?>? = null,

	@field:SerializedName("Balls")
	val balls: String? = null,

	@field:SerializedName("Runs")
	val runs: String? = null
) : Parcelable



@Parcelize
data class Officials(

	@field:SerializedName("Umpires")
	val umpires: String? = null,

	@field:SerializedName("Referee")
	val referee: String? = null
) : Parcelable

@Parcelize
data class PowerPlay(

	@field:SerializedName("PP1")
	val pP1: String? = null,

	@field:SerializedName("PP2")
	val pP2: String? = null
) : Parcelable

@Parcelize
data class TeamDetails(

	@field:SerializedName("Name_Short")
	val nameShort: String? = null,

	@field:SerializedName("Name_Full")
	val nameFull: String? = null,

	@field:SerializedName("Players")
	val players: HashMap<String,PlayerDetails>? = HashMap()
) : Parcelable

@Parcelize
data class PlayerDetails(

	@field:SerializedName("Iscaptain")
	val iscaptain: Boolean? = null,

	@field:SerializedName("Bowling")
	val bowling: Bowling? = null,

	@field:SerializedName("Position")
	val position: String? = null,

	@field:SerializedName("Batting")
	val batting: Batting? = null,

	@field:SerializedName("Name_Full")
	val nameFull: String? = null,

	@field:SerializedName("Name_Short")
	val nameShort: String? = null,

	@field:SerializedName("Iskeeper")
	val iskeeper: Boolean? = null
) : Parcelable
