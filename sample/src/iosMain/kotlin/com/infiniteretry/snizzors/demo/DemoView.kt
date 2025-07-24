@file:OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)

package com.infiniteretry.snizzors.demo

import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGRectMake
import platform.CoreGraphics.CGSizeMake
import platform.UIKit.NSLayoutConstraint
import platform.UIKit.NSTextAlignmentCenter
import platform.UIKit.UIAction
import platform.UIKit.UIButton
import platform.UIKit.UIButtonTypeSystem
import platform.UIKit.UIColor
import platform.UIKit.UIControlEventTouchUpInside
import platform.UIKit.UIControlStateNormal
import platform.UIKit.UIFont
import platform.UIKit.UIFontWeightSemibold
import platform.UIKit.UILabel
import platform.UIKit.UIView

class DemoView : UIView(frame = CGRectMake(0.0, 0.0, 0.0, 0.0)) {
  private val titleLabel: UILabel by lazy(LazyThreadSafetyMode.NONE) {
    UILabel().apply {
      setTranslatesAutoresizingMaskIntoConstraints(false)
      text = "UIKit Content"
      font = UIFont.boldSystemFontOfSize(48.0)
      textColor = UIColor.whiteColor
      setTextAlignment(NSTextAlignmentCenter)
      numberOfLines = 0
    }
  }

  private val actionButton: UIButton by lazy(LazyThreadSafetyMode.NONE) {
    val titles = listOf(
      "Tap me!",
      "Again, again!",
      "Ok that's enough.",
      "No really, stop.",
    )
    var titleIndex = 0

    UIButton.buttonWithType(UIButtonTypeSystem).apply {
      setTranslatesAutoresizingMaskIntoConstraints(false)
      setTitle(titles[titleIndex], forState = UIControlStateNormal)
      titleLabel?.setFont(UIFont.systemFontOfSize(22.0, weight = UIFontWeightSemibold))
      setTitleColor(UIColor.whiteColor(), forState = UIControlStateNormal)
      setBackgroundColor(UIColor(red = 0.9, green = 0.3, blue = 0.4, alpha = 1.0)) // Reddish-pink
      layer.setCornerRadius(25.0)
      layer.setShadowColor(UIColor.blackColor().CGColor)
      layer.setShadowOffset(CGSizeMake(0.0, 5.0))
      layer.setShadowRadius(10.0)
      layer.setShadowOpacity(0.3f)
      addAction(UIAction.actionWithHandler {
        titleIndex = if (titleIndex < titles.size - 1) titleIndex + 1 else 0
        setTitle(titles[titleIndex], forState = UIControlStateNormal)
      }, forControlEvents = UIControlEventTouchUpInside)
    }
  }

  init {
    backgroundColor = UIColor.colorWithRed(red = 0.0, green = 0.0, blue = 0.0, alpha = 0.3)
    layer.cornerRadius = 40.0
    layer.borderColor = UIColor.blackColor.CGColor
    layer.borderWidth = 4.0

    addSubview(titleLabel)
    NSLayoutConstraint.activateConstraints(listOf(
      titleLabel.leadingAnchor.constraintEqualToAnchor(leadingAnchor, constant = 16.0),
      titleLabel.trailingAnchor.constraintEqualToAnchor(trailingAnchor, constant = -16.0),
      titleLabel.topAnchor.constraintEqualToAnchor(topAnchor, constant = 16.0),
    ))

    addSubview(actionButton)
    NSLayoutConstraint.activateConstraints(listOf(
      actionButton.centerXAnchor.constraintEqualToAnchor(centerXAnchor),
      actionButton.widthAnchor.constraintEqualToConstant(220.0),
      actionButton.heightAnchor.constraintEqualToConstant(50.0),
      actionButton.bottomAnchor.constraintEqualToAnchor(bottomAnchor, constant = -24.0),
    ))
  }
}
